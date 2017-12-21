package com.bossien.mapper.tp;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.Company;
import com.bossien.entity.UserTrainRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * user_train_role 表数据库控制层接口
 *
 */
public interface UserTrainRoleMapper extends BaseMapper<UserTrainRole> {

	List<UserTrainRole> selectList(UserTrainRole userTrainRole);

}