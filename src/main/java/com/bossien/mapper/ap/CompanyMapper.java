package com.bossien.mapper.ap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.Company;
import org.apache.ibatis.annotations.Param;

/**
 *
 * ap_company 表数据库控制层接口
 *
 */
public interface CompanyMapper extends BaseMapper<Company> {

	Company selectByIntId(@Param(value = "intId") String intId);

	/**
	 * 查询当前公司下所有的子公司
	 * @param companyId
	 * @return
	 */
	String getChildCompanyIds(String companyId);

}