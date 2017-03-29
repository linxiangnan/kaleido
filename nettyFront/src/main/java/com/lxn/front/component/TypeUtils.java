package com.lxn.front.component;

import com.lxn.front.constant.Constant;
import com.lxn.front.constant.ErrorCode;
import com.lxn.front.exception.ApiException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lin Xiangnan on 2017/2/24.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Component
public class TypeUtils {

    @Autowired
    private Logger logger;

    Object transferType(String key, Object object, Class classType) throws ApiException {
        Object objectNew = object;
        Class classTypePackage = packClass(classType);
        try {

            switch (classTypePackage.getName()) {
                case Constant.PACKAGE_LONG_CLASS:
                    objectNew = Long.parseLong(objectNew.toString());
                    break;
                case Constant.PACKAGE_INT_CLASS:
                    objectNew = Integer.parseInt(objectNew.toString());
                    break;
                case Constant.PACKAGE_FLOAT_CLASS:
                    objectNew = Float.parseFloat(objectNew.toString());
                    break;
                case Constant.PACKAGE_DOUBLE_CLASS:
                    objectNew = Double.parseDouble(objectNew.toString());
                    break;
                case Constant.PACKAGE_BOOLEAN_CLASS:
                    objectNew = Boolean.parseBoolean(objectNew.toString());
                    break;
            }
            return classTypePackage.cast(objectNew);
        } catch (Exception e) {
            logger.error("【错误信息】——类型转换出现错误，" +
                    "传入名：" + key + "，原类型为：" + objectNew.getClass() + "，转换类型为：" + classTypePackage + "。");
            throw new ApiException(ErrorCode.CLASS_TRANSFER_ERROR, "类型转换出现错误，请检查数据类型。");
        }
    }


    public Class packClass(Class classType) throws ApiException {

        if (classType.isPrimitive()) {

            switch (classType.getName()) {
                case Constant.ORIGIN_BOOLEAN_CLASS:
                    classType = Boolean.class;
                    break;
                case Constant.ORIGIN_INT_CLASS:
                    classType = Integer.class;
                    break;
                case Constant.ORIGIN_LONG_CLASS:
                    classType = Long.class;
                    break;
                case Constant.ORIGIN_BYTE_CLASS:
                    classType = Byte.class;
                    break;
                case Constant.ORIGIN_FLOAT_CLASS:
                    classType = Float.class;
                    break;
                case Constant.ORIGIN_DOUBLE_CLASS:
                    classType = Double.class;
                    break;
                case Constant.ORIGIN_SHORT_CLASS:
                    classType = Short.class;
                    break;
                case Constant.ORIGIN_CHARACTER_CLASS:
                    classType = Character.class;
                    break;
                default:
                    throw new ApiException(ErrorCode.PACKAGE_CLASS_TRANSFER_ERROR, "未获得此基本类型的包装类。");
            }
        }
        return classType;

    }
}
