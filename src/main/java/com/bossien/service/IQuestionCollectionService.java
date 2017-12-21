package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.QuestionCollection;

import java.util.List;

/**
 *
 * question_collection 表数据服务层接口
 *
 */
public interface IQuestionCollectionService extends IService<QuestionCollection> {

    List<String> getIds(List<QuestionCollection> list);

    /**
     * 查询收藏列表
     * @param project_id
     * @param user_id
     * @return
     */
    List<QuestionCollection> selectList(String project_id, String user_id);
}