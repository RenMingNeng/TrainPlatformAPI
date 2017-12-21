package com.bossien.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.entity.Feedback;
import com.bossien.mapper.tp.FeedbackMapper;
import com.bossien.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Blog 表数据服务层接口实现类
 *
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

	@Autowired private FeedbackMapper feedbackMapper;
	

}