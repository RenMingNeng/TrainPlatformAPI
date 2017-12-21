package com.bossien.listener;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/26.
 */
public class Event001 extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> params;

    public Event001(Object source) {
        super(source);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
