package com.lxn.front.constant;

/**
 * Created by Lin Xiangnan on 2017/2/22.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class Constant {

    // Spring组件设置
    public static final String COMPONENT_LOGGER_NAME = "logger";    // log日志组件
    public static final String COMPONENT_RPC_DISPATCHER_NAME = "rpcDispatcher";    // RPC组件

    // 线程池设置
    public static final int SERVICE_THREAD_NUM = 64;    // 业务线程池
    public static final int NETTY_HANDLER_THREAD_NUM = 16;  // netty的default_handler线程池

    public static final Byte MESSAGE_TYPE = (byte) 0x12;
    public static final Byte SYSTEM_TYPE = (byte) 0x89;

    // in数据格式
    public static final String MESSAGE_ACTION_KEY = "netty_action";

    public static final Integer SUCCESS_CODE = 1;
    public static final Integer STOP_APP_CODE = 2222;
    public static final Integer UN_HANDLE_ERROR_CODE = -1;
    public static final Integer UN_HANDLE_ERROR_MESSAGE_ID = -1;
    public static final String MESSAGE_EXCEPTION_INFO_KEY = "message";
    public static final String MESSAGE_CODE_KEY = "code";
    public static final String MESSAGE_SPLIT_KEYWORD = "\\.";
    public static final String STORE_CLASS_KEY = "ownClass";
    public static final String LIST_KEY = "list";
    public static final String DATA_KEY = "data";
    public static final String LIST_TOTAL_NUM_KEY = "totalNum";
    public static final String LIST_CURRENT_PAGE_KEY = "currentPage";

    public static final int PROTO_HEAD_SIZE = 9;
    public static final int PROTO_MAX_FRAME_LENGTH = 65536;
    public static final int PROTO_LENGTH_FIELD_OFFSET = 5;
    public static final int PROTO_LENGTH_FIELD_LENGTH = 4;
    public static final int PROTO_LENGTH_ADJUSTMENT = 0;
    public static final int PROTO_INITIAL_BYTES_TO_STRIP = 0;


    public static final String ORIGIN_INT_CLASS = "int";
    public static final String ORIGIN_LONG_CLASS = "long";
    public static final String ORIGIN_BOOLEAN_CLASS = "boolean";
    public static final String ORIGIN_BYTE_CLASS = "byte";
    public static final String ORIGIN_CHARACTER_CLASS = "character";
    public static final String ORIGIN_DOUBLE_CLASS = "double";
    public static final String ORIGIN_FLOAT_CLASS = "float";
    public static final String ORIGIN_SHORT_CLASS = "short";

    public static final String PACKAGE_INT_CLASS = "java.lang.Integer";
    public static final String PACKAGE_LONG_CLASS = "java.lang.Long";
    public static final String PACKAGE_BOOLEAN_CLASS = "java.lang.Boolean";
    public static final String PACKAGE_BYTE_CLASS = "java.lang.Byte";
    public static final String PACKAGE_CHARACTER_CLASS = "java.lang.Character";
    public static final String PACKAGE_DOUBLE_CLASS = "java.lang.Double";
    public static final String PACKAGE_FLOAT_CLASS = "java.lang.Float";
    public static final String PACKAGE_SHORT_CLASS = "java.lang.Short";


    // RPC框架定义
    public static final String GRPC_PREFIX = "grpc_";

}
