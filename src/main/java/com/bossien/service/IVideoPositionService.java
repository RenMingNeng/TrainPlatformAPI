package com.bossien.service;

import com.baomidou.mybatisplus.service.IService;
import com.bossien.entity.VideoPosition;
import com.bossien.entity.request.SaveVideoPosition;

/**
 *
 * video_position 表数据服务层接口
 *
 */
public interface IVideoPositionService extends IService<VideoPosition>{
    /**
     * 查询单条记录
     * @param videoPosition
     * @return
     */
    VideoPosition selectOne(VideoPosition videoPosition);

    int saveUserVideoInfo(SaveVideoPosition svp);

}
