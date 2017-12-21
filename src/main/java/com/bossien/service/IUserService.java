package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * user 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

    User selectOne(User user);

    String getRoleIdByUserType(String user_type);
    User getOne(String id);
    /**
     * 查询公司下的userIds集合
     * @param param
     * @return
     */
    List<String> selectUserIds(Map<String,Object> param);
    /**
     * 统计单位下的学员数量
     *
     * @param params
     * @return
     */
    int selectUserCount(Map<String, Object> params);

}