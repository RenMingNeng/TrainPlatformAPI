package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.CompanyTj;

import java.util.List;


public interface ICompanyTjService extends IService<CompanyTj> {

    /**
     * 通过companyId查询统计信息
     * @param company_id
     * @return
     */
    CompanyTj selectOne(String company_id);
    /**
     * 查询列表
     * @param companyIds
     * @return
     */
    List<CompanyTj> selectList(List<String> companyIds);
    /**
     * 统计最新信息
     * @return
     */
    CompanyTj analyze(String company_id);

}
