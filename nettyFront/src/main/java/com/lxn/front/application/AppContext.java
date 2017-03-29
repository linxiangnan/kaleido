package com.lxn.front.application;

import com.lxn.front.exception.ApiException;
import com.lxn.front.constant.Constant;
import com.lxn.front.constant.ErrorCode;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lin Xiangnan on 2017/2/21.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class AppContext implements ApplicationContextAware {

    private static Set<Integer> operationIds;

    static {
        operationIds = new HashSet<>();
        operationIds.add(Constant.STOP_APP_CODE);
    }

    private static ClassPathXmlApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContext.applicationContext = (ClassPathXmlApplicationContext)applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static void operateApp(int code) throws ApiException {

        if (!operationIds.contains(code)) {
            throw new ApiException(ErrorCode.SYS_OPERATION_CODE_NOT_EXIST, "系统操作码不存在。");
        }

        if (code == Constant.STOP_APP_CODE) {
            stopApp();
        }
    }

    private static void stopApp() {
        applicationContext.close();
    }

}
