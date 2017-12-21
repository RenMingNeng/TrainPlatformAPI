package com.bossien.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.PropertiesUtils;
import com.bossien.common.util.QuestionsUtils;
import com.bossien.entity.*;
import com.bossien.entity.enumeration.ProjectTypeEnum;
import com.bossien.entity.enumeration.QuestionsTypeEmun;
import com.bossien.entity.request.ExerciseCourseQuestion;
import com.bossien.entity.request.PerfectExercise;
import com.bossien.entity.request.QuestionTypeList;
import com.bossien.mapper.ap.CourseQuestionMapper;
import com.bossien.mapper.ap.QuestionMapper;
import com.bossien.mapper.tp.ProjectCourseInfoMapper;
import com.bossien.service.ICourseExerciseService;
import com.bossien.service.ICourseQuestionService;
import com.bossien.service.IExerciseAnswersService;
import com.bossien.service.IQuestionCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service(value = "courseExerciseService")
public class CourseExerciseServiceImpl extends ServiceImpl<CourseQuestionMapper, CourseQuestion> implements ICourseExerciseService {

    @Autowired
    private CourseQuestionMapper courseQuestionMapper;
    @Autowired
    private ProjectCourseInfoMapper projectCourseInfoMapper;
    @Autowired
    private IQuestionCollectionService questionCollectionService;
    @Autowired
    private IExerciseAnswersService exerciseAnswersService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private MongoOperations mongoTemplate;
    @Autowired
    private ICourseQuestionService courseQuestionService;

    // 操作mongodb的集合名,等同于mysql中的表
    private final String collectionName_question_collection = "question_collection";
    private final String collectionName_question_wrong_answers = "question_wrong_answers";

    public Map<String, Object> selectCourseQuestion(Page<Map<Object, Object>> page, ExerciseCourseQuestion cq) {
        Map<String, Object> param=new HashMap<String, Object>();
        Map<String, Object> map=new HashMap<String, Object>();
        Map<String, Object> params=new HashMap<String, Object>();
        List<Question> questionlist=new ArrayList<Question>();
        List<Question> queslist=new ArrayList<Question>();
        //该用户在该项目下的试题收藏
        List<QuestionCollection> collections= questionCollectionService.selectList(cq.getProject_id(),cq.getUser_id());
        param.put("intCourseId",cq.getCourse_id());
        //查询课程下所有试题id
        List<CourseQuestion> qlist = courseQuestionMapper.selectList(param);
        List<String> qIdlist=new ArrayList<String>();
        if(qlist.size()>0){
            for(CourseQuestion cqu:qlist){
                if(cqu.getIntQuestionId()!=null){
                    qIdlist.add(cqu.getIntQuestionId());
                }
            }
        }
        if(qIdlist.size()>0){
            params.put("intIds",qIdlist);
            //统计课程的总题量
            queslist=questionMapper.selectList(params);
            questionlist=questionMapper.selectQuestionsList(page,params);
        }
        List<Map<String, Object>> list = QuestionsUtils.combinQuestion(questionlist, courseQuestionService.selectCourseIdByQuestionId(cq.getProject_id(),cq.getUser_id()),collections);

        if(list!=null && list.size()>0){
            map.put("count",queslist.size());
            map.put("datas",list);
        }
        return map;
    }

    public Map<String, Object> selectAllQuestion(Page<Map<Object, Object>> page, Map<String, Object> map) {
        List<String> questionIds = this.getQuestionIds(map);
        if(questionIds == null || questionIds.size()<1){
            return null;
        }
        return getQuestionMap(page,map,questionIds);
    }

    public Map<String, Object> selectNoExerciseQuestion(Page<Map<Object, Object>> page, Map<String, Object> map) {
        List<String> questionIds = this.getQuestionIds(map);
        if(questionIds == null || questionIds.size()<1){
            return null;
        }
        //获取已经练习过的试题
        List<ExerciseAnswers> exerciseAnswersList = exerciseAnswersService.selectList(map.get("project_id").toString(),map.get("user_id").toString());
        //获取已经练习过的试题Ids
        List<String> ids = exerciseAnswersService.getIds(exerciseAnswersList);
        if(ids != null && ids.size()>0){
            questionIds.removeAll(ids);
        }
        if(questionIds == null || questionIds.size()<1){
            return null;
        }
        return getQuestionMap(page,map,questionIds);
    }

    public Map<String, Object> getQuestionMap(Page<Map<Object, Object>> page, Map<String, Object> map, List<String> questionIds) {
        String projectId = map.get("project_id").toString();
        String userId = map.get("user_id").toString();
        //该用户在该项目下的试题收藏
        List<QuestionCollection> collections= questionCollectionService.selectList(projectId,userId);
        map.put("intIds",questionIds);
        map.put("chrValid", PropertiesUtils.getValue("is_valid"));
        List<Question> questions = questionMapper.selectList(page,map);
        List<Map<String, Object>> result = QuestionsUtils.combinQuestion(questions,
                courseQuestionService.selectCourseIdByQuestionId(projectId,userId),
                collections);
        Integer count = questionMapper.selectCount(map);
        map.clear();
        map.put("datas",result);
        map.put("count",count);
        map.put("project_id",projectId);
        map.put("user_id",userId);
        return map;
    }

    public List<String> getQuestionIds(Map<String, Object> map) {
        List<String> courseIds = projectCourseInfoMapper.selectCourseIds(map);
        if(courseIds == null || courseIds.size()<1){
            return null;
        }
        map.put("intCourseIds",courseIds);
        return courseQuestionMapper.selectQuestionIdList(map);
    }

    public List<Map<String, Object>> selectExerciseMap(PerfectExercise per) {
        List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        Map<String, Object> map=new HashMap<String, Object>();
        Map<String, Object> map2=new HashMap<String, Object>();
        Map<String, Object> map3=new HashMap<String, Object>();
        Map<String, Object> map4=new HashMap<String, Object>();
        Map<String, Object> params=new HashMap<String, Object>();
        Map<String, Object> danmap=new HashMap<String, Object>();

        if(per.getProject_id()!=null){
            params.put("project_id",per.getProject_id());
        }
        if(per.getUser_id()!=null){
            params.put("user_id",per.getUser_id());
        }
        List<String> questionIds = getQuestionIds(params);


        //题目类型：01.单选题 02.多选题 03.判断题 04.填空题 05.简答题 06.论述题 07.分析题
        danmap.put("intIds",questionIds);
        danmap.put("chrValid", ProjectTypeEnum.QuestionType_1.getValue());
        danmap.put("chrType",QuestionsTypeEmun.QUESTIONTYPE_SINGLE.getValue());
        int dancount = questionMapper.selectCount(danmap);

        map.put("questions_type",QuestionsTypeEmun.QUESTIONTYPE_SINGLE.getValue());
        map.put("project_id",per.getProject_id());
        map.put("questions_count",dancount);
        list.add(map);

        //多选题
        danmap.put("chrType",QuestionsTypeEmun.QUESTIONTYPE_MANY.getValue());
        int duocount = questionMapper.selectCount(danmap);
        map2.put("questions_type",QuestionsTypeEmun.QUESTIONTYPE_MANY.getValue());
        map2.put("project_id",per.getProject_id());
        map2.put("questions_count",duocount);
        list.add(map2);

        //判断题
        danmap.put("chrType",QuestionsTypeEmun.QUESTIONTYPE_JUDGE.getValue());
        int pancount = questionMapper.selectCount(danmap);
        map3.put("questions_type",QuestionsTypeEmun.QUESTIONTYPE_JUDGE.getValue());
        map3.put("project_id",per.getProject_id());
        map3.put("questions_count",pancount);
        list.add(map3);

        int wrongCount=queryEasyQuestion(per.getProject_id(),per.getUser_id());
        map4.put("questions_type",QuestionsTypeEmun.QUESTIONTYPE_YICUO.getValue());
        map4.put("project_id",per.getProject_id());
        map4.put("questions_count",wrongCount);
        list.add(map4);
        return list;
    }

    //查询易错题数量
    public int queryEasyQuestion(String projectId,String userId) {
        int count=0;
        if (null != projectId && !projectId.equals("") && userId != null) {
            //mongo里面查询我的错题数据
            Query query = new Query(new Criteria().andOperator(
                    Criteria.where("project_id").is(projectId),
                    Criteria.where("user_id").is(userId)
            ));
            List<QuestionWrongAnswers> questionWrongAnswerss = mongoTemplate.find(query, QuestionWrongAnswers.class, collectionName_question_wrong_answers);
            //获取所有的题目编号
            List<String> questionsList = getEasyQuestionIds(questionWrongAnswerss);
            count=questionsList.size();
        }
        return count;
    }

    public Map<String, Object> selectQuestionTypeExerciseMap(Page<Map<Object, Object>> page, QuestionTypeList qtl) {
        Map<String, Object> params=new HashMap<String, Object>();
        Map<String, Object> retult=new HashMap<String, Object>();
        List<Map<String, Object>> relist=new ArrayList<Map<String, Object>>();
        int questionCount=0;
        if(qtl.getProject_id()!=null){
            params.put("project_id",qtl.getProject_id());
        }
        if(qtl.getUser_id()!=null){
            params.put("user_id",qtl.getUser_id());
        }
        List<String> qIds = getQuestionIds(params);
        String qType=qtl.getQuestions_type();
        if(qType.equals(QuestionsTypeEmun.QUESTIONTYPE_YICUO.getValue())){//查询我的易错题
            if (null != qtl.getProject_id() && !("").equals(qtl.getProject_id()) && null != qtl.getUser_id()){
                //mongo里面查询我的错题数据
                Query query = new Query(new Criteria().andOperator(
                        Criteria.where("project_id").is(qtl.getProject_id()),
                        Criteria.where("user_id").is(qtl.getUser_id())
                ));
                List<QuestionWrongAnswers> questionWrongAnswerss = mongoTemplate.find(query, QuestionWrongAnswers.class,
                        collectionName_question_wrong_answers);
                //获取所有的题目编号
                List<String> questionIds = getEasyQuestionIds(questionWrongAnswerss);
                questionCount=questionIds.size();
                if(questionIds.size() > 0){
                    //mongo里面查询收藏数据
                    query = new Query(new Criteria().andOperator(
                            Criteria.where("project_id").is(qtl.getProject_id()),
                            Criteria.where("user_id").is(qtl.getUser_id())
                    ));
                    List<QuestionCollection> questionCollections = mongoTemplate.find(query, QuestionCollection.class,
                            collectionName_question_collection);

                    //根据题目编号查询所有的题
                    params.put("intIds", questionIds);
                    params.put("chrValid", "1");
                    //result = questionService.selectList(para, questionCollections);
                    List<Question> questions = questionMapper.selectQuestionsList(page, params);
                    relist= QuestionsUtils.combinQuestion(questions,
                            courseQuestionService.selectCourseIdByQuestionId(qtl.getProject_id(), qtl.getUser_id()),questionCollections);
                }
            }
        }else{//查询其他题型试题列表
            if(qIds!=null){
                //该用户在该项目下的试题收藏
                List<QuestionCollection> collections= questionCollectionService.selectList(qtl.getProject_id(),qtl.getUser_id());
                params.put("intIds", qIds);
                params.put("chrValid", "1");
                params.put("chrType", qtl.getQuestions_type());
                List<Question> questions = questionMapper.selectQuestionsList(page, params);
                questionCount=questions.size();
                relist= QuestionsUtils.combinQuestion(questions,courseQuestionService.selectCourseIdByQuestionId(qtl.getProject_id(), qtl.getUser_id()),collections);
            }

        }

        if(relist.size()>0 && !relist.isEmpty()){
            retult.put("datas",relist);
            retult.put("count",qtl.getQuestions_count());
            //retult.put("question_count",questionCount);
            retult.put("project_id",qtl.getProject_id());
            retult.put("user_id",qtl.getUser_id());
        }
        return retult;
    }


    /**
     * 查询出错3次的题
     * @param questionWrongAnswers
     * @return
     */
    public List<String> getEasyQuestionIds(List<QuestionWrongAnswers> questionWrongAnswers){
            List<String> questions = new ArrayList<String>();
            Map<String, Integer> result = new HashMap<String, Integer>();
            if(questionWrongAnswers.size() < 1){
                return questions;
            }
            for(QuestionWrongAnswers questionWrongAnswer : questionWrongAnswers){
                String question_id = questionWrongAnswer.getQuestion_id();
                int count = 0;
                if(null != result.get(question_id)){
                    count = result.get(question_id);
                }
                if(count > 1){//超过三次就就是易错题
                    questions.add(question_id);
                }
                result.put(question_id, ++count);
            }
            return questions;
    }


}
