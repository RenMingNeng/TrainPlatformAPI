package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.Company;
import com.bossien.mapper.ap.CompanyMapper;
import com.bossien.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * ap_company 表数据服务层接口实现类
 *
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

	@Autowired private CompanyMapper companyMapper;


	public Company selectByIntId(String intId) {
		return companyMapper.selectByIntId(intId);
	}

	public String getChildCompanyIds(String companyId) {

		return companyMapper.getChildCompanyIds(companyId);
	}
}