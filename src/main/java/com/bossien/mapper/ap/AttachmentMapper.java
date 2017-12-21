package com.bossien.mapper.ap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.Attachment;
import com.bossien.entity.CourseAttachment;

import java.util.List;
import java.util.Map;

public interface AttachmentMapper extends BaseMapper<Attachment> {
    /**
     * 通过courseId查询附件id
     * @param param
     * @return
     */
    List<Attachment> selectList(Map<String, Object> param);
}
