package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.UserRole;
import com.bossien.mapper.tp.UserRoleMapper;
import com.bossien.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * user_role 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

	@Autowired private UserRoleMapper userRoleMapper;

	public UserRole selectOne(UserRole userRole) {
		return userRoleMapper.selectOne(userRole);
	}
}