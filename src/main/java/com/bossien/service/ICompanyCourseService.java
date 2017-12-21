package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.CompanyCourse;
import com.bossien.entity.CompanyProject;

import java.util.List;
import java.util.Map;


public interface ICompanyCourseService extends IService<CompanyCourse> {

    /**
     * 根据CompanyId查询courseIds
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByCompanyId(Map<String, Object> map);
    /**
     * 统计公司下的课程数量
     *
     * @param map
     * @return
     */
    int selectCompanyCourseCount(Map<String, Object> map);
}