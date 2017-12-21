package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.CompanyCourse;
import com.bossien.entity.CompanyProject;
import com.bossien.mapper.ap.CompanyCourseMapper;
import com.bossien.mapper.tp.CompanyProjectMapper;
import com.bossien.service.ICompanyCourseService;
import com.bossien.service.ICompanyProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "companyCourseService")
public class CompanyCourseServiceImpl extends ServiceImpl<CompanyCourseMapper, CompanyCourse> implements ICompanyCourseService {

	@Autowired private CompanyCourseMapper companyCourseMapper;

	/**
	 * 根据CompanyId查询courseIds
	 *
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectByCompanyId(Map<String, Object> map) {
		return companyCourseMapper.selectByCompanyId(map);
	}
	/**
	 * 统计公司下的课程数量
	 *
	 * @param map
	 * @return
	 */
	@Override
	public int selectCompanyCourseCount(Map<String, Object> map) {
		return companyCourseMapper.selectCompanyCourseCount(map);
	}
}