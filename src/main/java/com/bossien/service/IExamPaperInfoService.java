package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.ExamPaperInfo;
import com.bossien.entity.ExamStrategy;

/**
 *
 * exam_paper_info 表数据服务层接口
 *
 */
public interface IExamPaperInfoService extends IService<ExamPaperInfo> {
    /**
     * 查询单条记录
     * @param examPaperInfo
     * @return
     */
    ExamPaperInfo selectOne(ExamPaperInfo examPaperInfo);

    /**
     * 统计个数
     * @param examPaperInfo
     * @return
     */
    int selectCount(ExamPaperInfo examPaperInfo);

    /**
     * 新增
     * @param exam_no
     * @param exam_type
     * @param examStrategy
     * @param userId
     * @param userName
     */
    void insert(String exam_no, String exam_type, ExamStrategy examStrategy, String userId, String userName);

    /**
     * 修改
     * @param exam_no
     */
    void update(String exam_no);
}
