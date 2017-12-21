package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.Company;
import com.bossien.entity.CompanyProject;

import java.util.List;


public interface ICompanyProjectService extends IService<CompanyProject> {

    List<String> selectProjectIdsByCompanyId(String company_id);
}