package com.bossien.mapper.ap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.CompanyCourseType;

import java.util.List;
import java.util.Map;

public interface CompanyCourseTypeMapper extends BaseMapper<CompanyCourseType> {

    List<Map<String, Object>> selectByParams(Map<String,Object> params);
}
