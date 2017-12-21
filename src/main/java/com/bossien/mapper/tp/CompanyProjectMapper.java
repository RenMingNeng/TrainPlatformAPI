package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.CompanyProject;
import com.bossien.entity.request.AdminProjectList;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */
public interface CompanyProjectMapper extends BaseMapper<CompanyProject> {

    List<String> selectProjectIdsByCompanyId(String company_id);

    /**
     * 查询某单位的所有项目id(带分页)
     * @param page
     * @param apj
     * @return
     */
    List<Map<Object,Object>> selectProjectIds(Page<Map<Object, Object>> page,AdminProjectList apj);
}
