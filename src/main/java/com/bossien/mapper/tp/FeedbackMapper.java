package com.bossien.mapper.tp;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.Feedback;

/**
 *
 * feedback 表数据库控制层接口
 *
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {

	List<Map<Object, Object>> selectMap(Page<Map<Object, Object>> page);

}