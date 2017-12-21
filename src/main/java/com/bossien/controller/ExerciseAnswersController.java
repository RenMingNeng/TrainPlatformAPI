package com.bossien.controller;

import com.bossien.common.anno.TokenSecurity;
import com.bossien.common.bean.Response;
import com.bossien.common.util.MapUtil;
import com.bossien.common.util.QuestionsUtils;
import com.bossien.entity.*;
import com.bossien.entity.request.ExerciseAnswersInfoJson;
import com.bossien.entity.request.ExerciseAnswersJson;
import com.bossien.entity.request.FindExerciseInfoJson;
import com.bossien.plugin.page.SpringDataPageable;
import com.bossien.service.*;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by A on 2017/10/18.
 */

@RestController
@RequestMapping("/api/v1.0/exercise")
@Api(value = "练习答题记录")
public class ExerciseAnswersController {
    public static final Logger logger = LoggerFactory.getLogger(ExerciseAnswersController.class);

    // 操作mongodb的集合名,等同于mysql中的表
    @Value("${collectionName_class_hours}")
    private String collectionName_class_hours;
    @Value("${collectionName_exercise_answers}")
    private String collectionName_exercise_answers;
    @Value("${mongo_db_question_wrong_answers}")
    private String collectionName_question_wrong_answers;
    @Value("${mongo_db_question_collection}")
    private String collectionName_question_collection;

    @Autowired
    private ISequenceService sequenceService;
    @Autowired
    private MongoOperations mongoTemplate;
    @Autowired
    private IPersonDossierService personDossierService;
    @Autowired
    private IProjectStatisticsInfoService projectStatisticsInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProjectExerciseOrderService projectExerciseOrderService;
    @Autowired
    private IProjectCourseInfoService projectCourseInfoService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IBaseService<ExerciseAnswers> baseService;
    @Autowired
    private ICourseQuestionService courseQuestionService;

    @TokenSecurity
    @ApiOperation(value = "1.1.14练习答题保存",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/submit", method= RequestMethod.POST)
    public Response submit(@RequestBody ExerciseAnswersJson exerciseAnswersJson){
        List<ExerciseAnswersInfoJson> answers = exerciseAnswersJson.getAnswers();
        if(answers.size() < 1){
            return new Response().failure("没有答题记录");
        }
        String project_id = exerciseAnswersJson.getProject_id();
        if(StringUtils.isEmpty(project_id)){
            return new Response().failure("项目编号不能为空");
        }
        String user_id = exerciseAnswersJson.getUser_id();
        if(StringUtils.isEmpty(user_id)){
            return new Response().failure("用户编号不能为空");
        }
        User user = new User();
        user.setId(user_id);
        user = userService.selectOne(user);
        if(null == user){
            return new Response().failure("用户不存在");
        }

        Iterator<ExerciseAnswersInfoJson> it = answers.iterator();
        while (it.hasNext()){
            ExerciseAnswersInfoJson exerciseAnswersInfoJson = it.next();
            ExerciseAnswers exerciseAnswers = baseService.build(user.getUser_name(), new ExerciseAnswers());
            exerciseAnswers.setQuestion_id(exerciseAnswersInfoJson.getQuestion_id());
            exerciseAnswers.setCourse_id(exerciseAnswersInfoJson.getCourse_id());
            exerciseAnswers.setAnswer(exerciseAnswersInfoJson.getAnswer());
            exerciseAnswers.setIs_correct(exerciseAnswersInfoJson.getIs_correct());
            exerciseAnswers.setProject_id(project_id);
            exerciseAnswers.setUser_id(user_id);
            insert(exerciseAnswers, project_id, user);
        }
        return new Response().success();
    }

    @TokenSecurity
    @ApiOperation(value = "1.1.15练习答题记录",response = Response.class, produces = "application/json")
    @RequestMapping(value = "/answer/page", method= RequestMethod.POST)
    public Response find_exercise_info(@RequestBody FindExerciseInfoJson findExerciseInfoJson){
        String project_id = findExerciseInfoJson.getProject_id();
        if(org.apache.commons.lang3.StringUtils.isEmpty(project_id)){
            return new Response().failure("项目编号不能为空");
        }

        String user_id = findExerciseInfoJson.getUser_id();
        if(StringUtils.isEmpty(user_id)){
            return new Response().failure("用户编号不能为空");
        }

        //1 查看答对试题 2 查看答错试题
        String is_correct = findExerciseInfoJson.getIs_correct();
        if(StringUtils.isEmpty(is_correct)){
            return new Response().failure("查询类型不能为空");
        }

        Integer answer_count = findExerciseInfoJson.getAnswer_count();
        if(null == answer_count){
            return new Response().failure("试题数量不能为空");
        }

        //获取所有的题目编号
        Set<String> questionSet = new HashSet<String>();
        List<QuestionCollection> questionCollections = new ArrayList<QuestionCollection>();
        List<ExerciseAnswers> exerciseAnswerss = new ArrayList<ExerciseAnswers>();
                //分页
        SpringDataPageable springDataPageable = new SpringDataPageable();
        int page_index = 1;
        springDataPageable.setPageNumber(page_index);
        springDataPageable.setPageSize(answer_count);

        //1 查看答对试题 2 查看答错试题
        Query query;
        if(is_correct.equals("1")){
            //mongo里面查询我的错题数据
            query = new Query(new Criteria().andOperator(
                    Criteria.where("project_id").is(project_id),
                    Criteria.where("user_id").is(user_id),
                    Criteria.where("is_correct").is("1")
            ));
        }else{
            //mongo里面查询我的错题数据
            query = new Query(new Criteria().andOperator(
                    Criteria.where("project_id").is(project_id),
                    Criteria.where("user_id").is(user_id),
                    Criteria.where("is_correct").is("2")
            ));
        }
        exerciseAnswerss = mongoTemplate.find(query.with(springDataPageable), ExerciseAnswers.class,
                collectionName_exercise_answers);
        if(exerciseAnswerss.size() > 0){
            for(ExerciseAnswers exerciseAnswers : exerciseAnswerss){
                questionSet.add(exerciseAnswers.getQuestion_id());
            }

            if(questionSet.size() > 0){
                //mongo里面查询收藏数据
                query = new Query(new Criteria().andOperator(
                        Criteria.where("project_id").is(project_id),
                        Criteria.where("user_id").is(user_id)
                ));
                questionCollections = mongoTemplate.find(query, QuestionCollection.class,
                        collectionName_question_collection);
            }
        }

        Map<String, Object> params = MapUtil.getInstance();
        params.put("intIds", questionSet);
        List<Question> questions = questionService.selectList(params);

        List<Map<String, Object>> questionMaps = QuestionsUtils.combinQuestion(questions,
                courseQuestionService.selectCourseIdByQuestionId(project_id, user_id),
                questionCollections);

        if(null == questionMaps || questionMaps.size() < 1){
            return new Response().failure("暂无数据");
        }

        return new Response().success(questionMaps);
    }

    //保存考试记录
    public void insert(ExerciseAnswers exerciseAnswers, String project_id, User user){
        boolean isInsert = insertClassHourse(project_id,
                exerciseAnswers.getCourse_id(),
                exerciseAnswers.getQuestion_id(),
                exerciseAnswers.getIs_correct(),
                user.getId(),
                user.getUser_name(),
                user.getCompany_id());
        if(isInsert){
            mongoTemplate.insert(exerciseAnswers, collectionName_exercise_answers);
        }
    }

    /**
     * 添加学时
     * @param projectId
     * @param courseId
     * @param questionId
     * @param isTrue
     * @param userId
     * @param userName
     * @param companyId
     * @return
     */
    public boolean insertClassHourse(String projectId, String courseId, String questionId,
                                     String isTrue, String userId, String userName, String companyId){
        //学时表：答对存1条，答错存1条；
        //答题表：答对存3条，答错存1条
        //错题表：答错存3条
        boolean result = true;
        String source = "";
        Long studyTime = 0l;
        Long allStudyTime = 0l; // 统计错题总学时  累加
        //答对数量
        long answerCorrectCount = mongoTemplate.count(new Query(new Criteria().andOperator(
                Criteria.where("project_id").is(projectId),
                Criteria.where("question_id").is(questionId),
                Criteria.where("user_id").is(userId),
                Criteria.where("is_correct").is("1")
        )), collectionName_exercise_answers);
        //答错数量
        long answerWrongCount = mongoTemplate.count(new Query(new Criteria().andOperator(
                Criteria.where("project_id").is(projectId),
                Criteria.where("question_id").is(questionId),
                Criteria.where("user_id").is(userId),
                Criteria.where("is_correct").is("2")
        )), collectionName_exercise_answers);

        if(isTrue.equals("1")){//答对
            source = "1";
            studyTime = 30l;
            allStudyTime = 30l;

            //判断答对个数
            if(answerCorrectCount > 2){
                return false;
            }
        }else{//答错
            source = "2";
            studyTime = 3l;
            allStudyTime = 3l;

            //答错3次就不添加练习记录
            if(answerWrongCount > 0){
                //不添加练习记录
                result = false;


                Query query = new Query(new Criteria().andOperator(
                        Criteria.where("course_id").is(courseId),
                        Criteria.where("project_id").is(projectId),
                        Criteria.where("user_id").is(userId),
                        Criteria.where("source").is("2")
                ));
                List<ClassHours> classHourss = mongoTemplate.find(query, ClassHours.class, collectionName_class_hours);
                if(null != classHourss && classHourss.size() > 0){//先删除后添加
                    allStudyTime = 3 + classHourss.get(0).getStudy_time();
                }
            }

            //答错记录表
            long wrongCount = mongoTemplate.count(new Query(new Criteria().andOperator(
                    Criteria.where("question_id").is(questionId),
                    Criteria.where("project_id").is(projectId),
                    Criteria.where("user_id").is(userId)
            )), collectionName_question_wrong_answers);

            if(wrongCount < 3){//错题个数小于3时，添加
                QuestionWrongAnswers questionWrongAnswers = new QuestionWrongAnswers(
                        projectId,
                        questionId,
                        userId,
                        userName,
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                );
                mongoTemplate.insert(questionWrongAnswers, collectionName_question_wrong_answers);
            }

            //先删除再增加
            mongoTemplate.remove(new Query(new Criteria().andOperator(
                    Criteria.where("course_id").is(courseId),
                    Criteria.where("project_id").is(projectId),
                    Criteria.where("user_id").is(userId),
                    Criteria.where("source").is("2")
            )), collectionName_class_hours);
        }
        // 插入学时记录
        ClassHours classHours = new ClassHours(
                projectId,
                courseId,
                userId,
                source,
                allStudyTime,
                userName,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        );
        mongoTemplate.insert(classHours, collectionName_class_hours);

        //计算 已答题量、答对题量 未答题量 答错题量
        Integer yetAnswered = 0;
        Integer correctAnswered = 0;
        Integer notAnswered = 0;
        Integer failAnswered = 0;
        if(answerCorrectCount == 0 && isTrue.equals("1")){
            correctAnswered += 1;
        }

        if(answerWrongCount == 0 && answerCorrectCount == 0 && isTrue.equals("2")){
            failAnswered += 1;
        }

        if(answerWrongCount > 0 && answerCorrectCount == 0 && isTrue.equals("1")){
            failAnswered -= 1;
        }

        if(answerCorrectCount == 0 && answerWrongCount == 0){
            yetAnswered += 1;
            notAnswered -=1;
        }

        //更新项目课程信息
        update_project_course_info(userId, userName, courseId, projectId, yetAnswered, correctAnswered, isTrue, studyTime);

        //更新个人统计信息
        boolean isUpdateTrainCount = update_project_statistics_info(userId, userName, projectId, yetAnswered, correctAnswered, isTrue, studyTime);

        //更新个人档案
        update_person_dossier(userId, userName, companyId, projectId, studyTime, isUpdateTrainCount);

        //更新练习排行信息
        update_project_exercise_order(userId, userName, projectId, yetAnswered, correctAnswered, notAnswered, failAnswered, isTrue, studyTime);

        return result;
    }

    /**
     * 更新个人档案
     * @param userId
     * @param userName
     * @param companyId
     * @param projectId
     * @param studyTime
     */
    public void update_person_dossier(String userId, String userName, String companyId, String projectId, Long studyTime, boolean isUpdateTrainCount){
        Map<String, Object> params = new ConcurrentHashMap<String, Object>();
        params.put("userId", userId);
        params.put("projectId", projectId);
        PersonDossier personDossier = personDossierService.selectOne(new PersonDossier(userId, companyId));

        if(null != personDossier) {
            personDossier.setUser_id(userId);
            personDossier.setCompany_id(companyId);
            personDossier.setYear_studytime(personDossier.getYear_studytime() + studyTime);
            personDossier.setTotal_studytime(personDossier.getYear_studytime() + studyTime);
            //更新必选题量
            if(isUpdateTrainCount){
                personDossier.setTrain_count(personDossier.getTrain_count() + 1);
            }
            personDossier.setOper_user(userName);
            personDossier.setOper_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            personDossierService.update(personDossier);
        } else {
            logger.error("QueueExerciseListener-----------personDossierMap not exist with args:"+new Gson().toJson(params));
        }
    }

    /**
     * 更新项目课程信息
     * @param userId
     * @param userName
     * @param courseId
     * @param projectId
     * @param studyTime
     */
    public void update_project_course_info(String userId, String userName, String courseId, String projectId,
                                           Integer yetAnswered, Integer correctAnswered, String isTrue, Long studyTime){
        Map<String, Object> params = new ConcurrentHashMap<String, Object>();
        params.put("projectId", projectId);
        params.put("courseId", courseId);
        params.put("userId", userId);
        ProjectCourseInfo projectCourseInfo = projectCourseInfoService.selectOne(new ProjectCourseInfo(projectId, courseId, userId));

        if(null != projectCourseInfo) {
            correctAnswered = projectCourseInfo.getCorrect_answered() + correctAnswered;
            yetAnswered = projectCourseInfo.getYet_answered() + yetAnswered;

            //总答正确率：答对总题量/已答题量*100
            Double correctRate = Double.parseDouble(new DecimalFormat("0.0").
                    format(correctAnswered * 100 / yetAnswered));

            //判断应修学时和已修学时，来更新finishStatus培训状态（-1未完成1完成）
            Long requirement_studytime = projectCourseInfo.getRequirement_studytime();
            Long totalStudyTime = projectCourseInfo.getTotal_studytime() + studyTime;
            String finishStatus = "-1";
            if(totalStudyTime >= requirement_studytime*60){
                finishStatus = "1";
            }

            projectCourseInfo = new ProjectCourseInfo(
                    projectCourseInfo.getId(),
                    projectCourseInfo.getProject_id(),
                    projectCourseInfo.getCourse_id(),
                    projectCourseInfo.getCourse_name(),
                    projectCourseInfo.getClass_hour(),
                    projectCourseInfo.getUser_id(),
                    requirement_studytime,
                    totalStudyTime,
                    projectCourseInfo.getAnswer_studytime() + studyTime,
                    projectCourseInfo.getTrain_studytime(),
                    projectCourseInfo.getTotal_question(),
                    yetAnswered,
                    correctAnswered,
                    correctRate,
                    finishStatus,
                    projectCourseInfo.getCreate_user(),
                    projectCourseInfo.getCreate_time(),
                    userName,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
            );

            projectCourseInfoService.update(projectCourseInfo);
        } else {
            logger.error("QueueExerciseListener-----------projectCourseInfo not exist with args:"+new Gson().toJson(params));
        }
    }

    /**
     * 更新个人统计信息
     * @param userId
     * @param userName
     * @param projectId
     * @param studyTime
     */
    public boolean update_project_statistics_info(String userId, String userName, String projectId,
                                                  Integer yetAnswered, Integer correctAnswered, String isTrue, Long studyTime){
        boolean result = false;
        ProjectStatisticsInfo projectStatisticsInfo = projectStatisticsInfoService.selectOne(new ProjectStatisticsInfo(projectId, userId));


        if(null != projectStatisticsInfo) {
            correctAnswered = projectStatisticsInfo.getCorrect_answered() + correctAnswered;
            yetAnswered = projectStatisticsInfo.getYet_answered() + yetAnswered;

            //总答正确率：答对总题量/已答题量*100
            Double correctRate = Double.parseDouble(new DecimalFormat("0.0").
                    format(correctAnswered * 100 / yetAnswered));

            //判断是否是第一次添加学时
            Long totalStudyTime = projectStatisticsInfo.getTotal_studytime();
            if(totalStudyTime == 0){
                result = true;
            }

            //检查培训状态
            String trainStatus = projectStatisticsInfo.getTrain_status();
            if(trainStatus.equals("1") && (totalStudyTime + studyTime) >= projectStatisticsInfo.getRequirement_studytime()*60){
                trainStatus = "2";
            }

            //判断应修学时和已修学时，来更新train_status培训状态（1未完成2完成）
            projectStatisticsInfo = new ProjectStatisticsInfo(
                    projectStatisticsInfo.getId(),
                    projectStatisticsInfo.getProject_id(),
                    projectStatisticsInfo.getProject_start_time(),
                    projectStatisticsInfo.getProject_end_time(),
                    projectStatisticsInfo.getUser_id(),
                    projectStatisticsInfo.getRole_id(),
                    projectStatisticsInfo.getRole_name(),
                    projectStatisticsInfo.getDept_name(),
                    projectStatisticsInfo.getRequirement_studytime(),
                    totalStudyTime + studyTime,
                    projectStatisticsInfo.getAnswer_studytime() + studyTime,
                    projectStatisticsInfo.getTrain_studytime(),
                    projectStatisticsInfo.getTotal_question(),
                    yetAnswered,
                    correctAnswered,
                    correctRate,
                    trainStatus,
                    projectStatisticsInfo.getExam_no(),
                    projectStatisticsInfo.getExam_time_info(),
                    projectStatisticsInfo.getExam_score(),
                    projectStatisticsInfo.getExam_status(),
                    projectStatisticsInfo.getCreate_user(),
                    projectStatisticsInfo.getCreate_time(),
                    userName,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
            );

            projectStatisticsInfoService.update(projectStatisticsInfo);
        }
        return result;
    }

    /**
     * 更新练习排行信息
     * @param userId
     * @param userName
     * @param projectId
     * @param yetAnswered
     * @param correctAnswered
     * @param notAnswered
     * @param failAnswered
     * @param isTrue
     * @param studyTime
     */
    public void update_project_exercise_order(String userId, String userName, String projectId,
                                              Integer yetAnswered, Integer correctAnswered, Integer notAnswered, Integer failAnswered, String isTrue, Long studyTime){
        Map<String, Object> params = new ConcurrentHashMap<String, Object>();
        params.put("projectId", projectId);
        params.put("userId", userId);
        ProjectExerciseOrder projectExerciseOrder = projectExerciseOrderService.selectOne(new ProjectExerciseOrder(projectId, userId));

        if(null != projectExerciseOrder) {
            correctAnswered = projectExerciseOrder.getCorrect_answered() + correctAnswered;
            yetAnswered = projectExerciseOrder.getYet_answered() + yetAnswered;

            //总答正确率：答对总题量/已答题量*100
            Double correctRate = Double.parseDouble(new DecimalFormat("0.0").
                    format(correctAnswered * 100 / yetAnswered));

            projectExerciseOrder = new ProjectExerciseOrder(
                    projectId,
                    userId,
                    projectExerciseOrder.getTotal_question(),
                    yetAnswered,
                    projectExerciseOrder.getNot_answered() + notAnswered,
                    correctAnswered,
                    projectExerciseOrder.getFail_answered() + failAnswered,
                    correctRate,
                    projectExerciseOrder.getAnswer_studytime() + studyTime,
                    userName,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
            );
            projectExerciseOrderService.update(projectExerciseOrder);
        }
    }
}
