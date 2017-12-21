package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.CourseAttachment;
import com.bossien.entity.CourseInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */
public interface ICourseAttachmentService extends IService<CourseAttachment> {
    /**
     * 通过courseId查询附件id
     * @param param
     * @return
     */
    List<String> selectAttachmentIds(Map<String,Object> param);

}
