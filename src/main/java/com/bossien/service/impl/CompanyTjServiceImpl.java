package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.StringUtil;
import com.bossien.entity.Company;
import com.bossien.entity.CompanyTj;
import com.bossien.mapper.tp.CompanyTjMapper;
import com.bossien.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CompanyTjServiceImpl extends ServiceImpl<CompanyTjMapper, CompanyTj> implements ICompanyTjService {

	@Autowired private CompanyTjMapper companyTjMapper;
	@Autowired private IUserService userService;
	@Autowired private IProjectStatisticsInfoService projectStatisticsInfoService;
	@Autowired private ICourseInfoService courseInfoService;
	@Autowired private ICompanyCourseService companyCourseService;
	@Autowired private ICompanyService companyService;

	@Override
	public CompanyTj selectOne(String company_id) {
		return companyTjMapper.selectOne(company_id);
	}

	@Override
    public List<CompanyTj> selectList(List<String> companyIds) {
		List<CompanyTj> CompanyTjs = new ArrayList<CompanyTj>();
		for(String companyId: companyIds){
			if(StringUtil.isEmpty(companyId)){
				continue;
			}
			CompanyTjs.add(analyze(companyId));
		}
        return CompanyTjs;
    }
	/**
	 * 统计最新信息
	 * @return
	 */
	@Override
	public CompanyTj analyze(String company_id){

		Map <String, Object> params = new ConcurrentHashMap<String, Object>();
		params.put("company_id", company_id);
		List <String> projectStatus = new ArrayList<String>();
		projectStatus.add("3");                                   //进行中
		projectStatus.add("4");                                   //已结束
		params.put("projectStatus", projectStatus);

		DecimalFormat df = new DecimalFormat("0.00");
		// 学员数量
		Integer countUser = userService.selectUserCount(params);

		//课程数量
		Integer countCourse = companyCourseService.selectCompanyCourseCount(params);
		//题目数量
		Integer countQuestion = courseInfoService.selectCourseQuestionCount(params);

		//累计学时
		Double totalClassHour = projectStatisticsInfoService.selecttotalClassHour(params);

		//年度累计学时
		Double totalYearClassHour = projectStatisticsInfoService.selecttotalYearClassHour(params);

		// 人均学时
		Double averagePersonClassHour = 0.00;
		if(countUser != 0){
			averagePersonClassHour = Double.parseDouble(new DecimalFormat("0.00").format(totalYearClassHour * 1.00 / countUser));
		}

		//公司信息
		Company company = companyService.selectByIntId(company_id);

		CompanyTj companyTj = new CompanyTj();
		companyTj.setCount_user(countUser.toString());
		companyTj.setCount_course(countCourse.toString());
		companyTj.setCount_question(countQuestion.toString());
		companyTj.setTotal_class_hour(totalClassHour.toString());
		companyTj.setTotal_year_class_hour(totalYearClassHour.toString());

		companyTj.setAverage_person_class_hour(averagePersonClassHour.toString());
		companyTj.setCompany_id(company_id);
		if(null != company && null != company.getVarName()){
			companyTj.setCompany_name(company.getVarName());
		}
		return companyTj;
	}
}
