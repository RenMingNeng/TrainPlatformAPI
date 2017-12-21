package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.UserRole;

import java.util.List;

/**
 *
 * user_role 表数据库控制层接口
 *
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

	UserRole selectOne(UserRole userRole);

}