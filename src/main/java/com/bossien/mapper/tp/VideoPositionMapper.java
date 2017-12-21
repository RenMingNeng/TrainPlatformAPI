package com.bossien.mapper.tp;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bossien.entity.VideoPosition;

/**
 *
 */
public interface VideoPositionMapper extends BaseMapper<VideoPosition> {

	/**
	 * 查询单条记录
	 * @param videoPosition
	 * @return
	 */
	VideoPosition selectOne(VideoPosition videoPosition);

	/**
	 * 插入视频播放位置信息
	 */
	int insert(VideoPosition videoPosition);

	/**
	 * 更新视频播放位置信息
	 */
	int update(VideoPosition videoPosition);
 }