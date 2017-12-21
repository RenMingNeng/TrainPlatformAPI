package com.bossien.mapper.ap;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bossien.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<Question> selectList(Map<String, Object> params);

    List<Question> selectQuestionsList(Page<Map<Object,Object>> page, Map<String, Object> param);

    /**
     * 分页查询试题
     * @param params
     * @return
     */
    List<Question> selectList(Page<Map<Object, Object>> page,Map<String, Object> params);

    /**
     * 查询试题数量
     * @param params
     * @return
     */
    Integer selectCount(Map<String, Object> params);

    List<Question> selectByIds(@Param(value = "ids") List<String> ids);
}
