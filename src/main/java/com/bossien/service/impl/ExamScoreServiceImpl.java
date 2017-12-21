package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.ExamPaperInfo;
import com.bossien.entity.ExamScore;
import com.bossien.entity.User;
import com.bossien.mapper.tp.ExamPaperInfoMapper;
import com.bossien.mapper.tp.ExamScoreMapper;
import com.bossien.mapper.tp.ProjectUserMapper;
import com.bossien.mapper.tp.UserMapper;
import com.bossien.service.IExamScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
@Service
public class ExamScoreServiceImpl extends ServiceImpl<ExamScoreMapper, ExamScore> implements IExamScoreService {
    @Autowired
    private ExamScoreMapper examScoreMapper;
    @Autowired
    private ProjectUserMapper projectUserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ExamPaperInfoMapper examPaperInfoMapper;
    /**
     * 查询单条记录
     * @param examScore
     * @return
     */

    public ExamScore selectOne(ExamScore examScore){
        return  examScoreMapper.selectOne(examScore);
    }

    public Integer userScoreCount(Map map) {

        return examScoreMapper.userScoreCount(map);
    }

    public List<Map<String, Object>> userScoreList(Map map) {
        String projectId = map.get("project_id").toString();
        List<Map<String, Object>> listMap;
        listMap = examScoreMapper.userScoreList(map);
        User user;
        // 重组map
        for (Map<String, Object> lMap : listMap) {
            user = new User();
            String userId = lMap.get("user_id").toString();
            user.setId(userId);
            // 人员姓名查询
            lMap.put("user_name",userMapper.selectOne(user).getUser_name());
            // 考试次数查询
            lMap.put("exam_count",this.selectExamCount(projectId,userId));
        }
        return listMap;
    }

    // 查询补考次数
    private Integer selectExamCount(String project_id, String user_id) {
        return examPaperInfoMapper.selectCount(
                new ExamPaperInfo(project_id, user_id, "2", "2"));
    }


    public Integer selectExamScore(ExamScore examScore) {
        return examScoreMapper.selectExamScore(examScore);
    }

    public List<ExamScore> selectList(ExamScore examScore) {
        return examScoreMapper.selectList(examScore);
    }
}
