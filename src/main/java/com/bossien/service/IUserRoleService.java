package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.Company;
import com.bossien.entity.UserRole;

/**
 *
 * user_role 表数据服务层接口
 *
 */
public interface IUserRoleService extends IService<UserRole> {

    UserRole selectOne(UserRole userRole);
}