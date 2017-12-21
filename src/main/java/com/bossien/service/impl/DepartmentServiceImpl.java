package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.Department;
import com.bossien.mapper.tp.DepartmentMapper;
import com.bossien.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * department 表数据服务层接口实现类
 *
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
	

}