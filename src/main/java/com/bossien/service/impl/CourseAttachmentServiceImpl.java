package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.MapUtil;
import com.bossien.entity.*;
import com.bossien.mapper.ap.CourseAttachmentMapper;
import com.bossien.mapper.ap.CourseQuestionMapper;
import com.bossien.mapper.tp.ProjectBasicMapper;
import com.bossien.mapper.tp.ProjectCourseMapper;
import com.bossien.mapper.tp.ProjectUserMapper;
import com.bossien.service.ICourseAttachmentService;
import com.bossien.service.ICourseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */

@Service
public class CourseAttachmentServiceImpl extends ServiceImpl<CourseAttachmentMapper, CourseAttachment> implements ICourseAttachmentService {

   @Autowired
    private CourseAttachmentMapper courseAttachmentMapper;

    public List<String> selectAttachmentIds(Map<String, Object> param) {
        return courseAttachmentMapper.selectAttachmentIds(param);
    }
}
