package com.bossien.listener;

import com.bossien.service.IProjectInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/26.
 */
public class EventListener implements ApplicationListener {

    private static Logger logger = LoggerFactory.getLogger(EventListener.class);

    /**
     * publishEvent触发该方法
     * @param event
     */
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof Event001) {        // 学员登陆之前查询当天需要校验项目状态的项目
            Event001 event001 = (Event001) event;
            this.handlerEvent001((Map)event001.getSource());
        }
        return;
    }

    private void handlerEvent001(Map<String, Object> params) {
        try {
            IProjectInfoService projectService = (IProjectInfoService)SystemContextLoaderListener.getBean("projectInfoService");
            Integer count = projectService.checkProjectStatus(params);
            logger.debug(MessageFormat.format("项目状态更新, 个数为{0}", count));
        } catch (Exception ex) {
            logger.error(MessageFormat.format("handlerEvent001 error", ex));
        }


    }

}
