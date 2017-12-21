package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.UserTrainRole;
import com.bossien.mapper.tp.UserTrainRoleMapper;
import com.bossien.service.IUserTrainRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * user_train_role 表数据服务层接口实现类
 *
 */
@Service
public class UserTrainRoleServiceImpl extends ServiceImpl<UserTrainRoleMapper, UserTrainRole> implements IUserTrainRoleService {

	@Autowired private UserTrainRoleMapper userTrainRoleMapper;

	public List<UserTrainRole> selectList(UserTrainRole userTrainRole) {
		return userTrainRoleMapper.selectList(userTrainRole);
	}

	public String getNames(List<UserTrainRole> userTrainRoles) {
		if(null == userTrainRoles || userTrainRoles.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder(1024);
		for(UserTrainRole userTrainRole: userTrainRoles) {
			sb.append(userTrainRole.getRole_name()).append(",");
		}
		if(sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}