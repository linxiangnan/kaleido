package com.lxn.front.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Lin Xiangnan on 2017/2/22.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamName {
    String value();
}
