package com.bossien.service.impl;

import com.bossien.common.util.DateUtils;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.*;
import com.bossien.entity.enumeration.ExamTypeEnum;
import com.bossien.entity.enumeration.QuestionsTypeEmun;
import com.bossien.mapper.ap.QuestionMapper;
import com.bossien.mapper.tp.ExamQuestionMapper;
import com.bossien.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */

@Service
public class ExamQuestionServiceImpl implements IExamQuestionService {

    @Autowired
    private ExamQuestionMapper examQuestionMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private IProjectUserService projectUserService;
    @Autowired
    private IProjectBasicService projectBasicService;
    @Autowired
    private IExamStrategyService examStrategyService;
    @Autowired
    private IExamPaperInfoService examPaperInfoService;
    @Autowired
    private IProjectStatisticsInfoService projectStatisticsInfoService;
    @Autowired
    private IProjectCourseService projectCourseService;
    @Autowired
    private ICourseQuestionService courseQuestionService;
    @Autowired
    private IBaseService<ExamQuestion> baseService;

    public String checkPaper(String project_id, String user_id, String exam_type) {
        if("null".equals(project_id) || "null".equals(exam_type))
            return "";
        // 检查是否有未考试试卷
        ExamPaperInfo examPaperInfo = examPaperInfoService.selectOne(new ExamPaperInfo(project_id, user_id, exam_type, "1"));
        if (null != examPaperInfo && null != examPaperInfo.getExam_no()) {
            return examPaperInfo.getExam_no();
        }

        ProjectUser projectUser = projectUserService.selectByProjectIdAndUserId(project_id, user_id);
        if(null == projectUser){
            return "您不在该考试项目中不能考试!";
        }
        //检查考试时间
        ProjectBasic projectBasic = projectBasicService.selectById(project_id);
        if(!checkExamTime(projectBasic)){
            return "不在考试时间范围之内不能考试!";
        }

        //根据examId查询组卷策略
        ExamStrategy examStrategy = examStrategyService.selectOne(new ExamStrategy(project_id, projectUser.getRole_id()));
        if(null == examStrategy){
            return "组卷策略不合法，无法组卷!";
        }

        //检测学时要求是否符合要求
        if(ExamTypeEnum.ExamType_2.getValue().equals(exam_type)){
            //考试次数检查
            Gson gson = new Gson();
            Map<String, Object> project_exam_info = gson.fromJson(projectBasic.getProject_exam_info(), Map.class);
            if(!checkExamCount(project_id, exam_type, String.valueOf(project_exam_info.get("count")), user_id)){
                return "您超过最大考试次数不能考试!";
            }

            if(!"0".equals(examStrategy.getNecessary_hour())){
                //totalQuestion 培训学时
                ProjectStatisticsInfo projectStatisticsInfo = projectStatisticsInfoService.selectOne(
                        new ProjectStatisticsInfo(project_id, user_id));
                //判断用户学时是否大于组卷策略中的必修学时要求
                if(examStrategy.getNecessary_hour() * 60 > projectStatisticsInfo.getTotal_studytime()){
                    return "没有达到学时要求，不能进行考试!";
                }
            }
        }
        return "";
    }

    public List<Question> createPaper(String project_id, String exam_no, String user_id, String user_name, String exam_type) {
        //获取角色
        ProjectUser projectUser = projectUserService.selectByProjectIdAndUserId(project_id, user_id);

        //根据examId查询组卷策略
        ExamStrategy examStrategy = examStrategyService.selectOne(new ExamStrategy(project_id, projectUser.getRole_id()));

        //判断必选题量跟组卷数量的合法性
        List<ProjectCourse> projectCourseList = projectCourseService.selectByProjectIdAndRoleId(new ProjectCourse(project_id, projectUser.getRole_id()));

        //组卷
        List<ExamQuestion> questions = checkDataLawful(project_id, examStrategy, projectCourseList, user_id, user_name);
        if(null != questions && questions.size() > 0){
            ExamQuestion examQuestion = questions.get(0);
            examQuestion = baseService.build(user_name,examQuestion);
            String questionIds = "";
            for (ExamQuestion e : questions) {
                questionIds += questionIds == "" ? e.getQuestions_id() : "," + e.getQuestions_id();
            }
            examQuestion.setQuestions_id(questionIds);
            examQuestion.setExam_no(exam_no);
            examQuestionMapper.insert(examQuestion);

            //添加考试详情
            examPaperInfoService.insert(exam_no, exam_type, examStrategy, user_id, user_name);

            Map<String, Object> params = MapUtil.getInstance();
            params.put("intIds", Arrays.asList(questionIds.split(",")));
            return questionMapper.selectList(params);
        }
        return new ArrayList<Question>();
    }

    public List<String> selectList(ExamQuestion examQuestion) {
        List<String> questions = examQuestionMapper.selectList(examQuestion);
        if(null != questions && questions.size() > 0){
            String questionIds = questions.get(0);
            return Arrays.asList(questionIds.split(","));
        }
        return new ArrayList<String>();
    }

    /**
     * 生成考试试卷
     */
    public List<ExamQuestion> makeExamPaper(Map<String, List<Question>> selectQuestionsByCourseId,
                                            Map<String, ProjectCourse> selectCourseByCourseId, ExamStrategy examStrategy,
                                            String project_id, String userId, String userName){
        List<ExamQuestion> select = new ArrayList<ExamQuestion>();

        //根据组卷策略、必选题数量生成必选题
        select.addAll(makeMustPaper(selectQuestionsByCourseId, examStrategy, selectCourseByCourseId, project_id, userId, userName));

        //去掉list中重复的考试题目
        Map<String, ExamQuestion> mlist = new HashMap<String, ExamQuestion>();
        for (int i=0; i < select.size(); i++) {
            ExamQuestion examQuestion = select.get(i);
            String questions_id = examQuestion.getQuestions_id();
            if (null != questions_id) {
                if (null != mlist.get(questions_id)) {
                    select.remove(i);
                    i--;
                }
                mlist.put(questions_id, examQuestion);
            }
        }
        return select;
    }

    /**
     * 根据组卷策略、必选题数量生成必选题
     */
    public List<ExamQuestion> makeMustPaper(Map<String, List<Question>> selectQuestionsByCourseId, ExamStrategy examStrategy,
                                            Map<String, ProjectCourse> selectCourseByCourseId, String project_id,
                                            String userId, String userName){
        List<ExamQuestion> select = new ArrayList<ExamQuestion>();
        List<Question> selectList = new ArrayList<Question>();
        List<Question> loseList = new ArrayList<Question>();

        //、courseAndQuestionMap，根据必选题量将一部分数据保存到考试表中，将已经保存的数据再courseAndQuestionMap中values中删除
        //获取role下面的 课程-考试课程表
        Iterator<String> itCourse = selectQuestionsByCourseId.keySet().iterator();
        while(itCourse.hasNext()){
            String courseId = itCourse.next();
            List<Question> questions = selectQuestionsByCourseId.get(courseId);

            ProjectCourse course = selectCourseByCourseId.get(courseId);
            int intSelectCount = 0;
            if(null != course){
                intSelectCount = course.getSelect_count();//必选题数量---判断是否为0
            }
            if(intSelectCount != 0){
                Collections.shuffle(questions);//洗牌
                //如果无法满足必选题量
                if(questions.size() < intSelectCount){
                    return new ArrayList<ExamQuestion>();
                }
                //根据必选题量获取题目------如果课程中包含相同的课程，数量照样
                List<Question> must = questions.subList(0, intSelectCount);
                select.addAll(mapToExamQuestion(must, project_id, userId, userName));
                selectList.addAll(must);
            }
            //将selectQuestionsByCourseId的每个角色中剩余values的数据集合lostQuestions
            loseList.addAll(questions.subList(intSelectCount, questions.size()));
        }

        Map<String, Integer> typeCount = getTypeCount(selectList);//统计上面必选题的 类型--数量
//        //根据组卷策略生产考卷(减去必选题的数量，然后随机抽取)
        select.addAll(makePaperByStrategy(loseList, examStrategy, typeCount, project_id, userId, userName));
        return select;
    }

    /**
     * 统计必选题的类型--数量
     * @param select
     * @return
     */
    public Map<String, Integer> getTypeCount(List<Question> select){
        Map<String, Integer> typeCount = MapUtil.getInstance();
        for (Question question : select) {
            String intQuestionsType = question.getChr_type();
            if(null != intQuestionsType){
                int count = 1;
                if(null != typeCount.get(intQuestionsType)){
                    count = typeCount.get(intQuestionsType);
                }
                typeCount.put(intQuestionsType, count);
            }
        }
        return typeCount;
    }

    /**
     * 根据组卷策略生产考卷(减去必选题的数量，然后随机抽取)
     * @return
     */
    public List<ExamQuestion> makePaperByStrategy(List<Question> questionList, ExamStrategy examStrategy,
                                                  Map<String, Integer> typeCount, String project_id, String userId, String userName){
        List<ExamQuestion> select = new ArrayList<ExamQuestion>();
        //根据角色，将单选、多选、判断题分开成map<role, map<type,list>> typeList
        Map<String, List<Question>> typeQuestionMap = getTypeQuestionMap(questionList);

        //在根据每个角色的组卷策略随机获取数据并保存
        Iterator<String> itType = typeQuestionMap.keySet().iterator();
        while(itType.hasNext()){
            String type = itType.next();
            List<Question> result = typeQuestionMap.get(type);
            int length = getSubLengthByStrategy(type, examStrategy, typeCount);
            //必选题量设置不合格
            if(length < 0){
                return new ArrayList<ExamQuestion>();
            }
            if(null != result && length > 0){
                if(result.size() < length){
//                    throw new RuntimeException("\"" + QuestionsTypeEmun.getName(type) + "\"数量不合法：" + length + "大于" + result.size());
                    return new ArrayList<ExamQuestion>();
                }
                Collections.shuffle(result);//洗牌
                select.addAll(mapToExamQuestion(result.subList(0, length), project_id, userId, userName));
            }
        }
        return select;
    }

    /**
     * 根据组卷策略获取 type 的题目要求数量
     * @param type
     * @param examStrategy
     * @param typeCount 已选的题量类型统计
     * @return
     */
    public int getSubLengthByStrategy(String type, ExamStrategy examStrategy, Map<String, Integer> typeCount){
        //根据类型获取组卷数量
        if(null == examStrategy)
            return 0;
        String questiontype_single = QuestionsTypeEmun.QUESTIONTYPE_SINGLE.getValue();
        String questiontype_many = QuestionsTypeEmun.QUESTIONTYPE_MANY.getValue();
        String questiontype_judge = QuestionsTypeEmun.QUESTIONTYPE_JUDGE.getValue();
        if(type.equals(questiontype_single))
            return examStrategy.getSingle_count() - getMustQuestionCount(typeCount, questiontype_single);
        if(type.equals(questiontype_many))
            return examStrategy.getMany_count() - getMustQuestionCount(typeCount, questiontype_many);
        if(type.equals(questiontype_judge))
            return examStrategy.getJudge_count() - getMustQuestionCount(typeCount, questiontype_judge);
        return 0;
    }

    /**
     * 过滤必选题的类型数量
     * @return
     */
    public int getMustQuestionCount(Map<String, Integer> typeCount, String type){
        if(null != typeCount){
            Integer count = typeCount.get(type);
            if(null != count){
                return count;
            }
        }
        return 0;
    }

    /**
     * 根据角色，将单选、多选、判断题分开成map<role, map<type,list>> typeList
     * @return
     */
    public Map<String, List<Question>> getTypeQuestionMap(List<Question> questionList){
        Map<String, List<Question>> typeQuestionMap = MapUtil.getInstance();
        for(Question question : questionList){
            String type = question.getChr_type();
            List<Question> data = new ArrayList<Question>();
            if(null != typeQuestionMap.get(type)){
                data = typeQuestionMap.get(type);
            }
            data.add(question);
            typeQuestionMap.put(type, data);
        }
        return typeQuestionMap;
    }

    /**
     * 将map转换成ExamQuestion对象
     * @param list
     * @param project_id
     * @param userId
     * @param userName
     * @return
     */
    public List<ExamQuestion> mapToExamQuestion(List<Question> list, String project_id, String userId, String userName){
        List<ExamQuestion> questions = new ArrayList<ExamQuestion>();
        for (int i = 0; i < list.size(); i++) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setProject_id(project_id);
            examQuestion.setUser_id(userId);
            examQuestion.setQuestions_id(list.get(i).getInt_id());
            questions.add(baseService.build(userName, examQuestion));
        }
        return questions;
    }

    /**
     * 判断合法性
     * @param project_id
     * @return
     */
    public List<ExamQuestion> checkDataLawful(String project_id, ExamStrategy examStrategy,
                                              List<ProjectCourse> projectCourseList, String userId, String userName){
        //先根据varExamId查询所有的 （课程与题库的关联表）集合
        List<Question> questions = courseQuestionService.selectQuestionByProjectAndUserId(project_id, userId);

        //去掉重复的(一个题目对应多个课程)
        questions = getDistinctList(questions);

        if(null == questions || questions.size() < 1){
            return new ArrayList<ExamQuestion>();
        }

        //将CourseAndQuestionMap修改成map<课程id,list题库集合>
        //map<question_id,course_id> courseQuestion
        Map<String, String> courseQuestion = courseQuestionService.selectCourseIdByQuestionId(project_id, userId);
        Map<String, List<Question>> selectQuestionsByCourseId = getCourseAndQuestionMap(courseQuestion, questions);

        //将roleCourseMap转换成map<课程id，ProjectCourse>（获取必选题数量）
        Map<String, ProjectCourse> selectCourseByCourseId = getCourseMap(projectCourseList);

        return makeExamPaper(selectQuestionsByCourseId, selectCourseByCourseId, examStrategy, project_id, userId, userName);
    }

    /**
     * 去掉集合中重复的
     * @return
     */
    public static List<Question> getDistinctList(List<Question> data){
        String ids = "";
        List<Question> result = new ArrayList<Question>();
        for (Question question : data) {
            if(ids.indexOf(question.getInt_id()) != -1){
                continue;
            }
            ids = ids == "" ? question.getInt_id() : "," + question.getInt_id();
            result.add(question);
        }
        return result;
    }

    /**
     * 将courseAndQuestionMap修改成map<课程id,list题库集合>
     *     courseQuestion
     * @return
     */
    public Map<String, List<Question>> getCourseAndQuestionMap(Map<String, String> courseQuestion, List<Question> questions){
        Map<String, List<Question>> result = MapUtil.getInstance();
        for (Question question : questions) {
            String questionId = question.getInt_id();
            String varCourseId = courseQuestion.get(questionId);
            if(null != varCourseId){
                List<Question> questionList = new ArrayList<Question>();
                if(null != result.get(varCourseId)){
                    questionList = result.get(varCourseId);
                }
                questionList.add(question);

                result.put(varCourseId, questionList);
            }
        }
        return result;
    }

    public Map<String, ProjectCourse> getCourseMap(List<ProjectCourse> projectCourseList){
        Map<String, ProjectCourse> result = MapUtil.getInstance();
        for (ProjectCourse projectCourse : projectCourseList) {
            String courseId = projectCourse.getCourse_id();
            if(null != courseId){
                result.put(courseId, projectCourse);
            }
        }
        return result;
    }

    /**
     * 查询考试对象，获取正式考试次数限制
     * @param project_id
     * @param exam_type
     * @param exam_count
     * @param user_id
     * @return
     */
    public boolean checkExamCount(String project_id, String exam_type, String exam_count, String user_id){
        // 判断正式考试次数合法
        int count = examPaperInfoService.selectCount(new ExamPaperInfo(project_id, user_id, exam_type, "2"));
        if(count >= Integer.parseInt(exam_count))
            return false;
        return true;
    }

    /**
     * //查询考试对象，获取正式考试次数限制
     * @param projectBasic
     * @return
     */
    public boolean checkExamTime(ProjectBasic projectBasic){
        if(null == projectBasic)
            return false;
        String exam_time = projectBasic.getProject_exam_info();
        if(null == exam_time){
            return false;
        }
        Gson gson = new Gson();
        Map<String, Object> time = gson.fromJson(exam_time, Map.class);
        String beginTime = String.valueOf(time.get("beginTime"));
        String endTime = String.valueOf(time.get("endTime"));
        if(DateUtils.parseDateTime(beginTime).getTime() <= System.currentTimeMillis() &&
                DateUtils.parseDateTime(endTime).getTime() >= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
}
