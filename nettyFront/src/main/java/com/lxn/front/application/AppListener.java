package com.lxn.front.application;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * Created by Lin Xiangnan on 2017/2/21.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class AppListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private Logger logger;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof ContextClosedEvent) {
            logger.info("【Application】：应用执行异步关闭操作。。。");
        } else if (event instanceof ContextRefreshedEvent) {
            logger.info("【Application】：应用初始化完成。。。");
        } else if (event instanceof ContextStartedEvent) {
            logger.info("【Application】：应用开始。。。");
        } else if (event instanceof ContextStoppedEvent) {
            logger.info("【Application】：应用停止。。。");
        } else {
            logger.info("【Application】：应用发生其他事件——" + event.getClass().getName() + "。。。");
        }
    }
}
