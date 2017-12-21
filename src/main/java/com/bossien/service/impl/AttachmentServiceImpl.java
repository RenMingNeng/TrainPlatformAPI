package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.PropertiesUtils;
import com.bossien.entity.Attachment;
import com.bossien.entity.CourseAttachment;
import com.bossien.mapper.ap.AttachmentMapper;
import com.bossien.mapper.ap.CourseAttachmentMapper;
import com.bossien.service.IAttachmentService;
import com.bossien.service.ICourseAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

   @Autowired
    private AttachmentMapper attachmentMapper;

    public List<Attachment> selectList(Map<String, Object> param) {
        return attachmentMapper.selectList(param);
    }
}
