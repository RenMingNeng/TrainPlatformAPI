package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.Attachment;
import com.bossien.entity.CourseAttachment;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzhaoyong on 2017/7/25.
 */
public interface IAttachmentService extends IService<Attachment> {
    /**
     * 通过courseId查询文件
     * @param param
     * @return
     */
    List<Attachment> selectList(Map<String, Object> param);

}
