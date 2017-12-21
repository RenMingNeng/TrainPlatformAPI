package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.CompanyTj;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
@Repository
public interface CompanyTjMapper extends BaseMapper<CompanyTj> {
    /**
     * 通过companyId查询统计信息
     * @param company_id
     * @return
     */
    CompanyTj selectOne(@Param(value = "company_id") String company_id);
    /**
     * 查询列表
     * @param params
     * @return
     */
    List<Map<String, Object>> selectList(Map<String, Object> params);
}
