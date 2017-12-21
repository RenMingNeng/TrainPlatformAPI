package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.DateUtils;
import com.bossien.entity.ExamPaperInfo;
import com.bossien.entity.ExamStrategy;
import com.bossien.mapper.tp.ExamPaperInfoMapper;
import com.bossien.service.IBaseService;
import com.bossien.service.IExamPaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 *
 * exam_paper_info 表数据服务层接口实现类
 *
 */
@Service
public class ExamPaperInfoServiceImpl extends ServiceImpl<ExamPaperInfoMapper, ExamPaperInfo> implements IExamPaperInfoService {
    @Autowired
    private ExamPaperInfoMapper examPaperInfoMapper;
    @Autowired
    private IBaseService<ExamPaperInfo> baseService;
    /**
     * 查询单条记录
     * @param examPaperInfo
     * @return
     */
    public ExamPaperInfo selectOne(ExamPaperInfo examPaperInfo){
        return examPaperInfoMapper.selectOne(examPaperInfo);
    }

    public int selectCount(ExamPaperInfo examPaperInfo) {

        return examPaperInfoMapper.selectCount(examPaperInfo);
    }

    public void insert(String exam_no, String exam_type, ExamStrategy examStrategy, String userId, String userName) {
        Map<String, Object> examPaperInfo = baseService.build(userName);
        examPaperInfo.put("exam_no", exam_no);
        examPaperInfo.put("user_id", userId);
        examPaperInfo.put("project_id", examStrategy.getProject_id());
        examPaperInfo.put("role_id", examStrategy.getRole_id());
        examPaperInfo.put("exam_type", exam_type);
        examPaperInfo.put("single_score", examStrategy.getSingle_score());
        examPaperInfo.put("many_score", examStrategy.getMany_score());
        examPaperInfo.put("judge_score", examStrategy.getJudge_score());
        examPaperInfo.put("fillout_score", examStrategy.getFillout_score());
        examPaperInfo.put("quesAns_score", examStrategy.getQues_ans_score());
        examPaperInfo.put("pass_score", examStrategy.getPass_score());
        examPaperInfo.put("total_score", examStrategy.getTotal_score());
        examPaperInfo.put("exam_duration", examStrategy.getExam_duration());
        examPaperInfo.put("exam_status", "1");
        examPaperInfo.put("necessary_Hour", examStrategy.getNecessary_hour());
        examPaperInfo.put("create_time", DateUtils.formatDateTime(new Date()));
        examPaperInfoMapper.insert(examPaperInfo);
    }

    public void update(String exam_no) {
        examPaperInfoMapper.update(exam_no);
    }
}
