package com.lxn.front.component;

import com.lxn.front.annotation.MethodName;
import com.lxn.front.annotation.ParamName;
import com.lxn.front.application.AppContext;
import com.lxn.front.constant.Constant;
import com.lxn.front.constant.ErrorCode;
import com.lxn.front.exception.ApiException;
import com.lxn.front.model.Message;
import com.lxn.front.model.PagedListResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lin Xiangnan on 2017/2/21.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Component("dispatcherAction")
public class DispatcherAction {

    @Autowired
    private Logger logger;

    @Autowired
    private TypeUtils typeUtils;

    private static Map<String, Map<String, Object>> methodMap = new HashMap();

    public Message callAction(int requestId, String className, String methodName, Map body) throws Exception {

        Method method = null;
        if (methodMap.containsKey(className) && methodMap.get(className).containsKey(methodName)) {
            method = (Method) methodMap.get(className).get(methodName);
        } else {
            Object bean;
            try {
                bean = AppContext.getBean(className);
            } catch (Exception e) {
                logger.error("【错误信息】——获取处理器出现错误，请检查传入action，传入action为：" + className + "." + methodName + "。");
                throw new ApiException(ErrorCode.GET_BEAN_ERROR, "获取处理器出现错误。");
            }
            if (bean == null) {
                logger.error("【错误信息】——获取处理器出现错误，请检查传入action，传入action为：" + className + "." + methodName + "。");
                throw new ApiException(ErrorCode.GET_BEAN_EMPTY, "未取得有效的处理器，请检查传入action。");
            }
            Method[] methods = bean.getClass().getMethods();
            for (Method methodEve : methods) {
                if (methodEve.getAnnotations() != null && methodEve.getAnnotations() instanceof Annotation[]) {
                    Annotation[] annotations = methodEve.getAnnotations();
                    MethodName methodNameAnno;
                    if (annotations != null && annotations.length > 0) {
                        methodNameAnno = (MethodName) methodEve.getAnnotations()[0];
                        if (methodNameAnno.value().equals(methodName)) {
                            method = methodEve;
                        }
                    }
                }
            }
            if (!methodMap.containsKey(className)) {
                Map<String, Object> newMethodMap = new HashMap<>();
                newMethodMap.put(methodName, method);
                newMethodMap.put(Constant.STORE_CLASS_KEY, bean);
                methodMap.put(className, newMethodMap);
            } else {
                methodMap.get(className).put(Constant.STORE_CLASS_KEY, bean);
                methodMap.get(className).put(methodName, method);
            }
        }

        if (method == null) {
            logger.error("【错误信息】——未取得有效的处理方法，请检查传入action，传入action为：" + className + "." + methodName + "。");
            throw new ApiException(ErrorCode.METHOD_NOT_FOUND, "未取得有效的处理方法，请检查传入action。");
        }
        List paramValues = new ArrayList();
        Annotation[][] annotationParams = method.getParameterAnnotations();
        Class[] classTypes = method.getParameterTypes();
        if (annotationParams.length != classTypes.length) {
            logger.error("【错误信息】——处理方法的参数数量与注解参数数量不匹配，请检查注解参数是否完整，" +
                    "实际方法参数数量为：" + classTypes.length + "，注解参数数量为：" + annotationParams.length + "。");
            throw new ApiException(ErrorCode.METHOD_PARAM_NUM_NOT_MATCH, "处理方法的参数数量与解析参数数量不匹配，请通知相应的管理员。");
        }
        for (int i = 0; i < annotationParams.length; i++) {
            Annotation[] annotationParam = annotationParams[i];
            Class classType = typeUtils.packClass(classTypes[i]);
            ParamName paramName = (ParamName) annotationParam[0];
            if (!body.containsKey(paramName.value())) {
                logger.error("【错误信息】——未取得有效的参数，请检查传入数据参数名，传入参数名集合为：[" + body.keySet().toString() + "]。");
                throw new ApiException(ErrorCode.METHOD_PARAM_NOT_FOUND, "未取得有效的参数，请检查传入数据参数名。");
            }
            Object value = body.get(paramName.value());
            Object transferedObj = typeUtils.transferType(paramName.value(), value, classType);
            if (!classType.isInstance(transferedObj)) {
                logger.error("【错误信息】——未取得有效的参数，请检查传入参数类型，" +
                        "传入参数名：" + paramName.value() + "，传入参数类型为：" + transferedObj.getClass() + "，要求类型为：" + classType + "。");
                throw new ApiException(ErrorCode.METHOD_PARAM_TYPE_ERROR, "未取得有效的参数，请检查各参数类型。");
            }
            paramValues.add(transferedObj);
        }
        Object ownClass = methodMap.get(className).get(Constant.STORE_CLASS_KEY);
        Object result = method.invoke(ownClass, paramValues.toArray());

        Map resultMap = new HashMap();
        if (result instanceof PagedListResult) {
            PagedListResult pagedListResult = (PagedListResult) result;
            resultMap.put(Constant.LIST_KEY, pagedListResult.getList());
            resultMap.put(Constant.LIST_TOTAL_NUM_KEY, pagedListResult.getTotalNum());
            resultMap.put(Constant.LIST_CURRENT_PAGE_KEY, pagedListResult.getCurrentPage());
        } else if (result instanceof List) {
            resultMap.put(Constant.LIST_KEY, result);
        } else if (result instanceof Map) {
            resultMap = (Map) result;
        } else {
            resultMap.put(Constant.DATA_KEY, result);
        }
        return new Message(Constant.MESSAGE_TYPE, requestId, resultMap);
    }

}
