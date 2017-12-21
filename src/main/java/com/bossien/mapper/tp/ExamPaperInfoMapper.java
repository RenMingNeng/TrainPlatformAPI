package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.ExamPaperInfo;

import java.util.Map;

/**
 *
 * exam_paper_info 表数据库控制层接口
 *
 */
public interface ExamPaperInfoMapper extends BaseMapper<ExamPaperInfo> {
    /**
     * 查询单条记录
	 * @param examPaperInfo
     * @return
     */
	ExamPaperInfo selectOne(ExamPaperInfo examPaperInfo);

	/**
	 * 新增
	 * @param params
	 */
	void insert(Map<String, Object> params);

	/**
	 * 统计
	 * @param examPaperInfo
	 * @return
	 */
	int selectCount(ExamPaperInfo examPaperInfo);

	/**
	 * 修改
	 * @param exam_no
	 */
	void update(String exam_no);
}