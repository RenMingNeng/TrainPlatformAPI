package com.bossien.mapper.ap;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.CourseAttachment;

import java.util.List;
import java.util.Map;

public interface CourseAttachmentMapper extends BaseMapper<CourseAttachment> {
    /**
     * 通过courseId查询附件id
     * @param param
     * @return
     */
    List<String> selectAttachmentIds(Map<String,Object> param);
}
