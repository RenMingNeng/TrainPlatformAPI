package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.Company;

/**
 *
 * ap_company 表数据服务层接口
 *
 */
public interface ICompanyService extends IService<Company> {

    Company selectByIntId(String intId);

    /**
     * 查询当前公司下所有的子公司
     * @param companyId
     * @return
     */
    String getChildCompanyIds(String companyId);
}