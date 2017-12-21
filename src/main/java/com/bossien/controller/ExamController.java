package com.bossien.controller;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.DateUtils;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.PropertiesUtils;
import com.bossien.common.util.QuestionsUtils;
import com.bossien.entity.*;
import com.bossien.entity.enumeration.ExamTypeEnum;
import com.bossien.entity.enumeration.ProjectTypeEnum;
import com.bossien.entity.request.*;
import com.bossien.plugin.token.TokenManager;
import com.bossien.service.*;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 考试信息控制器
 * @author fei.lv
 * @date 2017年10月18日 上午10:18:00
 */
@RestController
@RequestMapping("/api/v1.0/exam")
@Api(value = "考试")
public class ExamController {
    // 操作mongodb的集合名,等同于mysql中的表
    @Value("${collectionName_class_hours}")
    private String collectionName_class_hours;
    @Value("${collectionName_exam_answers}")
    private String collectionName_exam_answers;
    @Value("${mongo_db_question_wrong_answers}")
    private String collectionName_question_wrong_answers;
    /**
     * Token服务
     */
    @Resource(name="redisTokenManager") private TokenManager tokenManager;

    @Autowired
    private IUserService userService;
    @Autowired
    private IExamScoreService examScoreService;
    @Autowired
    private IExamPaperInfoService examPaperInfoService;
    @Autowired
    private IExamStrategyService examStrategyService;
    @Autowired
    private ISequenceService sequenceService;
    @Autowired
    private MongoOperations mongoTemplate;
    @Autowired
    private IPersonDossierService personDossierService;
    @Autowired
    private IExamDossierInfoService examDossierService;
    @Autowired
    private IProjectInfoService projectInfoService;
    @Autowired
    private IProjectBasicService projectBasicService;
    @Autowired
    private IProjectStatisticsInfoService projectStatisticsInfoService;
    @Autowired
    private ICourseQuestionService courseQuestionService;
    @Autowired
    private IExamQuestionService examQuestionService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IQuestionCollectionService questionCollectionService;
    @Autowired
    private IExamAnswersService examAnswersService;
    @Autowired
    private IBaseService<ExamAnswers> examAnswersBaseService;
    @Autowired
    private IBaseService<ExamScore> examScoreBaseService;
    @Autowired
    private IProjectUserService projectUserService;
    /**
     * 考试信息接口
     * @param examInfoJson
     * @return
     */
    @TokenSecurity
    @ApiOperation(value = "考试信息",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/result", method= RequestMethod.POST)
    public  Response result(
            @RequestBody ExamInfoJson examInfoJson
            ){

        String user_id = examInfoJson.getUser_id();
        String project_id = examInfoJson.getProject_id();
        String exam_type = examInfoJson.getExam_type();


        if("".equals(user_id) || user_id == null){
            return new Response().failure("用户Id不能为空");
        }
        if("".equals(project_id) || project_id == null){
            return new Response().failure("项目Id不能为空");
        }
        if("".equals(exam_type) || exam_type == null){
            return new Response().failure("考试类型不能为空");
        }
        //查询用户
        ProjectUser projectUser = null;

        projectUser = projectUserService.selectByProjectIdAndUserId(project_id,user_id);
        if(null == projectUser) {
            return new Response().failure("没有项目用户信息");
        }
       //查询项目基本信息
        ProjectBasic projectBasic = null;

        projectBasic = projectBasicService.selectOne(project_id);
        if(null == projectBasic ){
            return new Response().failure("项目不存在");
        }
        //查询组卷策略
        ExamStrategy examStrategy = null;

        examStrategy = examStrategyService.selectOne(new ExamStrategy(project_id,projectUser.getRole_id()));
        if(null == examStrategy ){
            return new Response().failure("组卷策略不存在");
        }

        //考试试题数量
        Integer countQuestion = examStrategy.getSingle_count() + examStrategy.getMany_count()
                                + examStrategy.getJudge_count();

        //查询考试分数记录
        ExamScore examScore = null;

        examScore = examScoreService.selectOne(new ExamScore(project_id,user_id,exam_type));


        Map<String,Object> map = new Gson().fromJson(projectBasic.getProject_exam_info(),Map.class);

        Map resp = MapUtil.getInstance();
        MapUtil.put(resp, "exam_new_score", examScore!=null?examScore.getScore():null);
        MapUtil.put(resp, "exam_pass_score", examStrategy.getPass_score());
        MapUtil.put(resp, "exam_duration", examScore!=null?examScore.getExam_duration():null);
        MapUtil.put(resp, "exam_endTime", map.get("endTime").toString());
        MapUtil.put(resp, "user_name", projectUser.getUser_name());
        MapUtil.put(resp, "exam_roleId", examStrategy.getRole_id());
        MapUtil.put(resp, "exam_time", examStrategy.getExam_duration());
        MapUtil.put(resp, "project_name", projectBasic.getProject_name());
        MapUtil.put(resp, "exam_questions_count", countQuestion);
        MapUtil.put(resp, "user_id", user_id);
        MapUtil.put(resp, "project_id", project_id);

        return new Response().success(resp);
    }

    /**
     * 查看考试成绩统计
     */
    @TokenSecurity
    @ApiOperation(value = "考试成绩统计",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/analyze", method=RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header"),})
    public  Response analyze(@RequestBody PerfectExercise perfectExercise){
        if(StringUtils.isEmpty(perfectExercise.getProject_id())){
            return new Response().failure("项目编号不能为空");
        }
        if(StringUtils.isEmpty(perfectExercise.getUser_id())){
            return new Response().failure("用户编号不能为空");
        }
        //查询考试项目试题总体量
        Integer questionCount = projectStatisticsInfoService.selectQuestions(new ProjectStatisticsInfo(perfectExercise.getProject_id(),perfectExercise.getUser_id()));
        if(questionCount == null){
            questionCount = 0;
        }
        //查询用户考试中最好成绩
        Integer exam_score = examScoreService.selectExamScore(new ExamScore(perfectExercise.getProject_id(),perfectExercise.getUser_id()));
        //查询考试记录
        List<ExamScore> result = examScoreService.selectList(new ExamScore(perfectExercise.getProject_id(),perfectExercise.getUser_id()));
        for (ExamScore examScore: result) {
            if((PropertiesUtils.getValue("is_passed")).equals(examScore.getIs_passed())){
                examScore.setIs_passed("合格");
            }else{
                examScore.setIs_passed("不合格");
            }
        }
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("project_id").is(perfectExercise.getProject_id()),
                Criteria.where("user_id").is(perfectExercise.getUser_id())
        ));
        Set<String> questionIdList = new HashSet<String>();
        Set<String> questionCorrect = new HashSet<String>();
        //获取考试答题记录数据
        List<ExamAnswers> examAnswersList = mongoTemplate.find(query,ExamAnswers.class,collectionName_exam_answers);
        for (ExamAnswers examAnswers: examAnswersList) {
            //往集合添加已答的题过滤掉重复的题
            questionIdList.add(examAnswers.getQuestion_id());
            //往集合添加答对的题过滤掉重复的题
            if((PropertiesUtils.getValue("is_correct")).equals(examAnswers.getIs_correct())){
                questionCorrect.add(examAnswers.getQuestion_id());
            }
        }
        //已答题量
        Integer yet_answered = questionIdList.size();
        //未答题量
        Integer not_answered = questionCount - yet_answered;
        //答对题量
        Integer correct_answered = questionCorrect.size();
        //答错题量
        Integer fail_answered = yet_answered - correct_answered;
        Map resp = MapUtil.getInstance();
        MapUtil.put(resp,"question_count",questionCount);
        MapUtil.put(resp,"yet_answered",yet_answered);
        MapUtil.put(resp,"not_answered",not_answered);
        MapUtil.put(resp,"correct_answered",correct_answered);
        MapUtil.put(resp,"fail_answered",fail_answered);
        MapUtil.put(resp,"best_score",exam_score);
        MapUtil.put(resp,"datas",result);
        return new Response().success(resp);
    }

    @TokenSecurity
    @ApiOperation(value = "1.19考试试卷信息(生成考卷)",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/page", method=RequestMethod.POST)
    public Response create(@RequestBody CreatePaperJson createPaperJson){
        String project_id = createPaperJson.getProject_id();
        if(StringUtils.isEmpty(project_id)){
            return new Response().failure("项目编号不能为空");
        }

        String user_id = createPaperJson.getUser_id();
        if(StringUtils.isEmpty(user_id)){
            return new Response().failure("用户编号不能为空");
        }

        User user = new User();
        user.setId(user_id);
        user = userService.selectOne(user);
        if(null == user){
            return new Response().failure("用户不存在");
        }

        String exam_type = createPaperJson.getExam_type();
        if(StringUtils.isEmpty(exam_type)){
            return new Response().failure("考试类型不能为空");
        }

        String result = examQuestionService.checkPaper(project_id, user_id, exam_type);
        List<Map<String, Object>> questionMaps;
        if(result.equals("")){
            result = sequenceService.generator();
            questionMaps = QuestionsUtils.combinQuestion(
                    examQuestionService.createPaper(project_id, result, user_id, user.getUser_name(), exam_type),
                    courseQuestionService.selectCourseIdByQuestionId(project_id, user_id),
                    questionCollectionService.selectList(project_id,user_id));
        }else{
            if(result.indexOf("!") != -1){//检查未通过的，生成考卷失败
                return new Response().failure(result);
            }else{
                List<String> questions = examQuestionService.selectList(new ExamQuestion(result));
                Map<String, Object> params = MapUtil.getInstance();
                params.put("intIds", questions);
                questionMaps = QuestionsUtils.combinQuestion(questionService.selectList(params),
                        courseQuestionService.selectCourseIdByQuestionId(project_id, user_id),
                        questionCollectionService.selectList(project_id,user_id));
            }
        }

        if(null == questionMaps || questionMaps.size() < 1){
            return new Response().failure("暂无数据");
        }

        ExamPaperInfo examPaperInfo = examPaperInfoService.selectOne(new ExamPaperInfo(result));

        Map resp = MapUtil.getInstance();
        MapUtil.put(resp,"datas", questionMaps);
        MapUtil.put(resp,"exam_no", result);
        MapUtil.put(resp,"question_score_dan", examPaperInfo.getSingle_score());
        MapUtil.put(resp,"question_score_duo", examPaperInfo.getMany_score());
        MapUtil.put(resp,"question_score_pan", examPaperInfo.getJudge_score());
        MapUtil.put(resp,"pass_score", examPaperInfo.getPass_score());
        MapUtil.put(resp,"exam_duration", examPaperInfo.getExam_duration());
        MapUtil.put(resp,"count", questionMaps.size());
        MapUtil.put(resp,"project_id", project_id);
        MapUtil.put(resp,"user_id", user_id);
        return new Response().success(resp);
    }

    @TokenSecurity
    @ApiOperation(value = "1.1.20考试答题保存",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/submit", method= RequestMethod.POST)
    public Response submit(@RequestBody ExamAnswersJson examAnswersJson){

        String exam_no = examAnswersJson.getExam_no();
        if(StringUtils.isEmpty(exam_no)){
            return new Response().failure("试卷编号不能为空");
        }

        ExamPaperInfo examPaperInfo = examPaperInfoService.selectOne(new ExamPaperInfo(exam_no));
        if(null == examPaperInfo){
            return new Response().failure("试卷不存在");
        }
        if(examPaperInfo.getExam_status().equals("2")){
            return new Response().failure("该试卷无效");
        }

        String project_id = examAnswersJson.getProject_id();
        if(StringUtils.isEmpty(project_id)){
            return new Response().failure("项目编号不能为空");
        }

        String user_id = examAnswersJson.getUser_id();
        if(StringUtils.isEmpty(user_id)){
            return new Response().failure("用户编号不能为空");
        }

        User user = new User();
        user.setId(user_id);
        user = userService.selectOne(user);
        if(null == user){
            return new Response().failure("用户不存在");
        }

        String exam_time = examAnswersJson.getExam_time();
        String exam_type = examAnswersJson.getExam_type();
        String exam_score = examAnswersJson.getExam_score();
        String exam_duration = examAnswersJson.getExam_duration();
        String examStatus = "1";
        String isPass = "1";//1是2否
        if(Integer.parseInt(exam_score) >= Integer.parseInt(examPaperInfo.getPass_score())){
            examStatus = "2";
        }else{
            examStatus = "3";
            isPass = "2";
        }

        List<ExamAnswersQuestionJson> answers = examAnswersJson.getAnswers();
        if(answers.size() > 0){
            //查询项目中所有题跟课程的关联关系
            Iterator<ExamAnswersQuestionJson> it = answers.iterator();
            while (it.hasNext()){
                ExamAnswersQuestionJson examAnswersQuestion = it.next();

                String question_id = examAnswersQuestion.getQuestion_id();
                String course_id = examAnswersQuestion.getCourse_id();
                String answer = examAnswersQuestion.getAnswer();
                String is_correct = examAnswersQuestion.getIs_correct();
                Integer question_score = examAnswersQuestion.getQuestion_score();

                ExamAnswers examAnswers = examAnswersBaseService.build(user.getUser_name(), new ExamAnswers());
                examAnswers.setQuestion_id(question_id);
                examAnswers.setAnswer(answer);
                examAnswers.setIs_correct(is_correct);
                examAnswers.setScore(question_score);
                examAnswers.setProject_id(project_id);
                insert(examAnswers, project_id, course_id, exam_no, user);
            }
        }

        //保存考试成绩
        save_paper_score(exam_duration, examPaperInfo, exam_no, Integer.valueOf(exam_score),
                isPass, exam_time, user.getId(), user.getUser_name());

        //更新考卷中考试状态
        examPaperInfoService.update(exam_no);

        //跟新人员档案培训次数
        update_person_dossier(user.getId(), user.getUser_name(), project_id);

        //正式考试
        if(ExamTypeEnum.ExamType_2.getValue().equals(exam_type)){
            Date create_time = DateUtils.parseDateTime(exam_time);
            double exam_length = Double.parseDouble(exam_duration) * 60 * 1000;
            String examEndTime = DateUtils.formatDateTime(create_time.getTime() + (long)exam_length);
            String examTimeInfo = examPaperInfo.getCreate_time() + "至" + examEndTime;

            ProjectStatisticsInfo projectStatisticsInfo = projectStatisticsInfoService.selectOne(
                    new ProjectStatisticsInfo(project_id, user_id));

            if(null == projectStatisticsInfo) {
                return new Response().failure("个人统计表不存在");
            }

            //修改考试信息表
            updateExamDossierInfo(project_id, examStatus, projectStatisticsInfo);

            //修改个人学时统计表
            update_project_statistics_info(user.getUser_name(), exam_no, examTimeInfo,exam_score, examStatus, projectStatisticsInfo);
        }

        return new Response().success();
    }

    @TokenSecurity
    @ApiOperation(value = "2.11查看试卷答题记录",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/answer/page", method= RequestMethod.POST)
    public Response page(@RequestBody ViewPaperJson viewPaperJson){
        String exam_no = viewPaperJson.getExam_no();
        if(StringUtils.isEmpty(exam_no)){
            return new Response().failure("试卷编号不能为空");
        }
        String user_id = viewPaperJson.getUser_id();
        if(StringUtils.isEmpty(user_id)){
            return new Response().failure("用户编号不能为空");
        }

        ExamPaperInfo examPaperInfo = examPaperInfoService.selectOne(new ExamPaperInfo(exam_no));
        if(null == examPaperInfo){
            return new Response().failure("试卷不存在");
        }
        List<String> questions = examQuestionService.selectList(new ExamQuestion(exam_no, user_id));
        if(null == questions || questions.size() < 1){
            return new Response().failure("试卷、用户不匹配");
        }

        Map<String, Object> params = MapUtil.getInstance();
        params.put("intIds", questions);
        List<Map<String, Object>> questionMaps = QuestionsUtils.combinQuestion(
                questionService.selectList(params),
                courseQuestionService.selectCourseIdByQuestionId(examPaperInfo.getProject_id(), user_id),
                questionCollectionService.selectList(examPaperInfo.getProject_id(), user_id),
                examAnswersService.selectList(exam_no, user_id));

        if(null == questionMaps || questionMaps.size() < 1){
            return new Response().failure("暂无数据");
        }
        return new Response().success(questionMaps);
    }

    //保存考试记录
    public void insert(ExamAnswers examAnswers, String project_id, String course_id, String exam_no, User user){
        insertClassHourse(examAnswers, course_id, user.getId(), user.getUser_name());

        //保存答题记录
        examAnswers.setUser_id(user.getId());
        examAnswers.setProject_id(project_id);
        examAnswers.setExam_no(exam_no);
        mongoTemplate.insert(examAnswers, collectionName_exam_answers);
    }

    //保存考试成绩
    public void save_paper_score(String examDuration, ExamPaperInfo examPaperInfo,
                                 String examNo, Integer score, String isPassed, String exam_time,
                                 String userId, String userName){
        ExamScore examScore = new ExamScore(
                examPaperInfo.getProject_id(),
                examNo,
                userId,
                examPaperInfo.getExam_type(),
                score,
                exam_time,
                isPassed,
                Double.parseDouble(examDuration)
        );
        examScoreService.insert(examScoreBaseService.build(userName, examScore));
    }

    /**
     * 更新个人统计信息
     * @param userName
     * @param examNo //试卷编号
     * @param examTimeInfo //用户考试时间：开始时间+结束时间
     * @param examScore //考试成绩
     * @param examStatus //考试状态
     * @param projectStatisticsInfo
     */
    public void update_project_statistics_info(String userName, String examNo, String examTimeInfo,
                                               String examScore, String examStatus, ProjectStatisticsInfo projectStatisticsInfo){
        //成绩没有记录的高时不修改
        if(null != projectStatisticsInfo.getExam_score() && !projectStatisticsInfo.getExam_score().equals("")
                && !projectStatisticsInfo.getExam_score().equals("\\") &&
                Integer.parseInt(projectStatisticsInfo.getExam_score()) >= Integer.parseInt(examScore)){
            return;
        }

        //执行修改
        projectStatisticsInfo.setExam_no(examNo);
        projectStatisticsInfo.setExam_time_info(examTimeInfo);
        projectStatisticsInfo.setExam_score(examScore);
        projectStatisticsInfo.setExam_status(examStatus);
        projectStatisticsInfo.setOper_user(userName);
        projectStatisticsInfo.setOper_time(DateUtils.formatDateTime(new Date()));
        projectStatisticsInfoService.update(projectStatisticsInfo);
    }

    /**
     * 修改考试信息表
     * @param projectId
     * @param examStatus
     * @param projectStatisticsInfo
     */
    public void updateExamDossierInfo(String projectId, String examStatus, ProjectStatisticsInfo projectStatisticsInfo){
        //原本合格状态，不需要继续
        if(projectStatisticsInfo.getExam_status().equals("2") ||
                (projectStatisticsInfo.getExam_status().equals("3") && examStatus.equals("3"))){
            return;
        }
        //更新考试信息表
        ExamDossierInfo examDossierInfoOld = examDossierService.selectOne(projectId);
        if(null == examDossierInfoOld){
            ProjectInfo projectInfo = projectInfoService.selectProjectInfoById(projectId);
            examDossierInfoOld = new ExamDossierInfo(projectId, projectInfo.getPerson_count());
            examDossierService.insert(examDossierInfoOld);
        }
        Integer yetExamCount = examDossierInfoOld.getYet_exam_count();    //已考人数
        Integer notExamCount = examDossierInfoOld.getNot_exam_count();    //未考人数
        Integer qualifiedCount = examDossierInfoOld.getQualified_count();  //合格人数
        Integer unqualifiedCount = examDossierInfoOld.getUnqualified_count(); //不合格人数
        //当为考试时
        if(null == projectStatisticsInfo.getExam_score() || projectStatisticsInfo.getExam_score().equals("")
                || projectStatisticsInfo.getExam_score().equals("\\")){
            ++yetExamCount;
            --notExamCount;
        }
        //当考试状态是未考试（1）和不合格（3）时，且当前状态是合格状态时
        if(projectStatisticsInfo.getExam_status().equals("1") && examStatus.equals("2")){
            ++qualifiedCount;
        }
        if(projectStatisticsInfo.getExam_status().equals("1") && examStatus.equals("3")){
            ++unqualifiedCount;
        }
        if(projectStatisticsInfo.getExam_status().equals("3") && examStatus.equals("2")){
            ++qualifiedCount;
            --unqualifiedCount;
        }
        examDossierService.update(new ExamDossierInfo(projectId, yetExamCount, notExamCount, qualifiedCount, unqualifiedCount));
    }

    /**
     * 添加学时
     * @param examAnswers
     * @param user_id user_name
     */
    public void insertClassHourse(ExamAnswers examAnswers, String course_id, String user_id, String user_name){
        String source = "";
        Long study_time = 0l;
        String is_correct = examAnswers.getIs_correct();
        if(is_correct.equals("1")){//答对
            source = "3";
            study_time = 30l;
        }else{//答错
            source = "4";
            study_time = 3l;

            //答错记录表
            long wrongCount = mongoTemplate.count(new Query(new Criteria().andOperator(
                    Criteria.where("question_id").is(examAnswers.getQuestion_id()),
                    Criteria.where("project_id").is(examAnswers.getProject_id()),
                    Criteria.where("user_id").is(user_id)
                    )),
                    collectionName_question_wrong_answers
            );
            if(wrongCount < 3){
                QuestionWrongAnswers questionWrongAnswers = new QuestionWrongAnswers(
                        examAnswers.getProject_id(),
                        examAnswers.getQuestion_id(),
                        user_id,
                        user_name,
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                );
                mongoTemplate.insert(questionWrongAnswers, collectionName_question_wrong_answers);
            }
        }
        // 插入学时记录
        ClassHours classHours = new ClassHours(
                examAnswers.getProject_id(),
                course_id,
                user_id,
                source,
                study_time,
                user_name,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        );
        mongoTemplate.insert(classHours, collectionName_class_hours);
    }

    /**
     * 更新个人档案中培训次数
     * @param userId
     */
    public void update_person_dossier(String userId, String userName, String projectId){
        ProjectInfo projectInfo = projectInfoService.selectProjectInfoById(projectId);
        //只检查考试项目
        if(projectInfo.getProject_type().equals(ProjectTypeEnum.QuestionType_3.getValue())){
            //修改培训次数
            int count = examPaperInfoService.selectCount(new ExamPaperInfo(projectId, userId, "2"));
            if(count < 2){
                PersonDossier personDossier = personDossierService.selectOne(new PersonDossier(userId));

                if(null != personDossier) {
                    personDossier.setUser_id(userId);
                    personDossier.setCompany_id(personDossier.getCompany_id());
                    Object train_count = personDossier.getTrain_count();
                    if(null != train_count){
                        personDossier.setTrain_count(personDossier.getTrain_count() + 1);
                    }
                    personDossier.setOper_user(userName);
                    personDossier.setOper_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    personDossierService.update(personDossier);
                }
            }
        }
    }
}
