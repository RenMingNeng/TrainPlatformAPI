package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.ExamScore;
import com.bossien.entity.ProjectBasic;
import com.bossien.mapper.tp.ExamScoreMapper;
import com.bossien.mapper.tp.ProjectBasicMapper;
import com.bossien.service.IProjectBasicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * project_Basic 表数据服务层实现类
 *
 */
@Service
public class ProjectBasicServiceImpl extends ServiceImpl<ProjectBasicMapper, ProjectBasic> implements IProjectBasicService {
    @Autowired
    private ProjectBasicMapper projectBasicMapper;
    public  ProjectBasic selectOne( String id){
        return projectBasicMapper.selectOne(id);
    }
}
