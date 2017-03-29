package com.lxn.front.component;

import com.lxn.front.constant.Constant;
import org.springframework.stereotype.Component;

/**
 * Created by Lin Xiangnan on 2017/3/14.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Component
public class InvokeUtils {

    public String getGrpcBeanName(String className) {
        return Constant.GRPC_PREFIX + className;
    }

}
