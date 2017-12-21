package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.LatelyStudyRecord;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface LatelyStudyMapper extends BaseMapper<LatelyStudyRecord> {


   int insert(Map<String,Object> param);

   int update(Map<String,Object> param);

	/**
	 * 查询单条记录
	 * @param param
	 * @return
	 */
   LatelyStudyRecord selectOne(Map<String,Object> param);
	/**
	 * 查询最近学习的课程
	 * @param param
	 * @return
	 */
	List<String> selectLatelyStudyCourse(Map<String,Object> param);

	int queryStudyCount(Map<String,Object> param);

	/**
	 * 查询最近学习的课程 带分页
	 * @param page
	 * @param param
	 * @return
	 */
	List<Map<Object,Object>> selectLatelyStudyRecord(Page<Map<Object, Object>> page, Map<String,Object> param);

 }