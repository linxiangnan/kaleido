package com.lxn.front.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Lin Xiangnan on 2017/2/21.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class ApplicationStart {

    public static void main(String[] args) {

        // 启动spring初始化
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 注册spring的事件
        ctx.registerShutdownHook();
    }

}
