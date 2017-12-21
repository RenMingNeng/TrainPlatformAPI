package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import java.util.List;

/**
 *
 * user 表数据库控制层接口
 *
 */
public interface UserMapper extends BaseMapper<User> {

	User selectOne(User user);
	User getOne(@Param(value = "id") String id);

    List<String> selectUserIdByDeptId(String dept_id);
	/**
	 * 查询公司下的userIds集合
	 * @param param
	 * @return
	 */
	List<String>  selectUserIds(Map<String,Object> param);

    List<String> selectUserIdByCompanyId(String company_id);
	/**
	 * 统计单位下的学员数量
	 *
	 * @param params
	 * @return
	 */
	int selectUserCount(Map<String, Object> params);

}