package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.PropertiesUtils;
import com.bossien.entity.Role;
import com.bossien.entity.User;
import com.bossien.mapper.tp.UserMapper;
import com.bossien.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 *
 * user 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	// 人员类型-培训组织人员(单位管理员)
	private static final String user_type_1 = PropertiesUtils.getValue("user_type_1");
	// 人员类型-培训监督人员(单位监管员)
	private static final String user_type_2 = PropertiesUtils.getValue("user_type_2");
	// 人员类型-培训学习人员(单位学员)
	private static final String user_type_3 = PropertiesUtils.getValue("user_type_3");

	@Autowired private UserMapper userMapper;

	@Autowired
	private Role super_vise;
	@Autowired
	private Role company_admin;
	@Autowired
	private Role company_user;

	public User selectOne(User user) {
		return userMapper.selectOne(user);
	}

	public String getRoleIdByUserType(String user_type) {
		if(StringUtils.isEmpty(user_type))
			return "";
		if(user_type_1.equals(user_type))// 人员类型-培训组织人员(单位管理员)
			return company_admin.getId();
		if(user_type_2.equals(user_type))// 人员类型-培训监督人员(单位监管员)
			return super_vise.getId();
		if(user_type_3.equals(user_type))// 人员类型-培训学习人员(单位学员)
			return company_user.getId();
		return "";
	}

	public User getOne(String id) {
		return userMapper.getOne(id);
	}

	/**
	 * 获取公司下的userIds
	 *
	 * @param params
	 * @return
	 */
	@Override
	public List<String> selectUserIds(Map<String, Object> params) {
		params.put("isValid", "1");                //有效
		params.put("userType", "3");                //学员
		return userMapper.selectUserIds(params);
	}

	/**
	 * 统计单位下的学员数量
	 *
	 * @param params
	 * @return
	 */
	@Override
	public int selectUserCount(Map<String, Object> params) {
		params.put("isValid", "1");                //有效
		params.put("userType", "3");                //学员
		return userMapper.selectUserCount(params);
	}
}