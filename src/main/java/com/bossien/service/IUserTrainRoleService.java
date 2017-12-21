package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.UserTrainRole;

import java.util.List;

/**
 *
 * user_train_role 表数据服务层接口
 *
 */
public interface IUserTrainRoleService extends IService<UserTrainRole> {

    List<UserTrainRole> selectList(UserTrainRole userTrainRole);

    String getNames(List<UserTrainRole> userTrainRoles);
}