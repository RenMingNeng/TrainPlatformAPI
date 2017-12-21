package com.bossien.mapper.ap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.CompanyCourse;
import java.util.List;
import java.util.Map;

public interface CompanyCourseMapper extends BaseMapper<CompanyCourse>{

   List<String> selectCourseIds(Map<String,Object> params);
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
