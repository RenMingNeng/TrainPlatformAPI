package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.Company;
import com.bossien.entity.CompanyProject;
import com.bossien.mapper.ap.CompanyMapper;
import com.bossien.mapper.tp.CompanyProjectMapper;
import com.bossien.service.ICompanyProjectService;
import com.bossien.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "companyProjectService")
public class CompanyProjectServiceImpl extends ServiceImpl<CompanyProjectMapper, CompanyProject> implements ICompanyProjectService {

	@Autowired private CompanyProjectMapper companyProjectMapper;


	public List<String> selectProjectIdsByCompanyId(String company_id) {

		return companyProjectMapper.selectProjectIdsByCompanyId(company_id);
	}
}