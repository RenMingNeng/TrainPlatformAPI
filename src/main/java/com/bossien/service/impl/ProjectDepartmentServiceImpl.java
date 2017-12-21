package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.Company;
import com.bossien.entity.ExamScore;
import com.bossien.entity.ProjectDepartment;
import com.bossien.entity.ProjectStatisticsInfo;
import com.bossien.mapper.ap.CompanyMapper;
import com.bossien.mapper.tp.*;
import com.bossien.service.IProjectDepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * project_department 表数据服务层接口实现类
 *
 */
@Service
public class ProjectDepartmentServiceImpl extends ServiceImpl<ProjectDepartmentMapper, ProjectDepartment> implements IProjectDepartmentService{

	@Autowired
	private ProjectDepartmentMapper projectDepartmentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProjectUserMapper projectUserMapper;
	@Autowired
	private ProjectStatisticsInfoMapper projectStatisticsInfoMapper;
	@Autowired
	private CompanyMapper companyMapper;

	public Integer selectCountByParams(Map map) {

		return projectDepartmentMapper.selectCountByParams(map);
	}

	public List<Map<String, Object>> selectListByParams(Map map) {
		String project_id = map.get("project_id").toString();
		String company_id = map.get("company_id").toString();
		List<Map<String, Object>> listMap = projectDepartmentMapper.selectListByParams(map);
		// 重组map
		if(null != listMap && listMap.size() > 0){
			for (Map<String, Object> lMap : listMap) {
				String dept_id = lMap.get("dept_id").toString();
				List<String> user_ids = userMapper.selectUserIdByDeptId(dept_id);
				// 查询部门下学员集合
				lMap.put("user_count",this.selectDeptUser(user_ids,project_id));
				// 查询考试合格人数
				lMap.put("pass_score_count",this.selectPassScoreCount(user_ids,project_id));
			}
		}else{       // 当没有部门的时候查询全公司学员的学习进度(部门名称默返回公司名称)
			if(StringUtils.isEmpty(map.get("dept_name").toString())){
				List<String> user_ids = userMapper.selectUserIdByCompanyId(company_id);
				Map map1 = MapUtil.getInstance();
				map1.put("company_id",company_id);
				map1.put("user_count",this.selectDeptUser(user_ids,project_id));
				map1.put("dept_name",companyMapper.selectByIntId(company_id).getVarName());
				map1.put("dept_id","0");
				map1.put("pass_score_count",this.selectPassScoreCount(user_ids,project_id));
				listMap.add(map1);
			}
		}
		return listMap;
	}

	public Integer studyProgressCount(Map map) {

		return projectDepartmentMapper.selectCountByParams(map);
	}

	public List<Map<String, Object>> studyProgressList(Map map) {
		String project_id = map.get("project_id").toString();
		String company_id = map.get("company_id").toString();
		List<Map<String, Object>> listMap = projectDepartmentMapper.selectListByParams(map);
		// 重组map
		if(null != listMap && listMap.size() > 0){
			for (Map<String, Object> lMap : listMap) {
				String dept_id = lMap.get("dept_id").toString();
				List<String> user_ids = userMapper.selectUserIdByDeptId(dept_id);
				// 查询部门下学员数量
				lMap.put("user_count",this.selectDeptUser(user_ids,project_id));
				// 完成培训人数
				lMap.put("complete_user_count",this.selectCompleteUserCount(user_ids,project_id));
			}
		}else{
			if(StringUtils.isEmpty(map.get("dept_name").toString())){
				List<String> user_ids = userMapper.selectUserIdByCompanyId(company_id);
				Map map1 = MapUtil.getInstance();
				map1.put("company_id",company_id);
				map1.put("user_count",this.selectDeptUser(user_ids,project_id));
				map1.put("dept_name",companyMapper.selectByIntId(company_id).getVarName());
				map1.put("dept_id","0");
				map1.put("complete_user_count",this.selectCompleteUserCount(user_ids,project_id));
				listMap.add(map1);
			}
		}
		return listMap;
	}

	public List<ProjectDepartment> selectListByProjectId(String id) {
		Map map = MapUtil.getInstance();
		map.put("project_id",id);
		return projectDepartmentMapper.selectListByProjectId(map);
	}


	// 查询（项目中）部门下的学员数量
	private Integer selectDeptUser(List<String> user_ids, String project_id) {
		Map map = MapUtil.getInstance();
		map.put("project_id",project_id);
		map.put("user_ids",user_ids);
		Integer count = projectUserMapper.selectDeptUserCount(map);
		return projectUserMapper.selectDeptUserCount(map);
	}


	// 查询（项目中）部门下考试合格人数
	private Integer selectPassScoreCount(List<String> user_ids,String project_id) {
		Map map = MapUtil.getInstance();
		map.put("project_id",project_id.trim());
		map.put("exam_status","2");
		map.put("user_ids",user_ids);
		return projectStatisticsInfoMapper.selectUserCountByParam(map);
	}

	// 完成（项目中）部门下完成培训人数
	public  Integer selectCompleteUserCount(List<String> user_ids,String project_id) {
		Integer count = 0;
		Map map = MapUtil.getInstance();
		map.put("project_id",project_id);
		map.put("countTrainCompleteYes","60");
		map.put("user_ids",user_ids);
		count = projectStatisticsInfoMapper.selectUserCountByParam(map);
		return count;
	}
}