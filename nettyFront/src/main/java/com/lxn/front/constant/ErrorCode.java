package com.lxn.front.constant;

/**
 * Created by Lin Xiangnan on 2017/2/22.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class ErrorCode {

    // Netty的通信Message相关
    public static final int MESSAGE_LESS_THAN_HEAD_SIZE = -10001;// 可读信息段小于头部信息!
    public static final int MESSAGE_ACTUAL_LENGTH_NOT_EQUAL_SET_LENGTH = -10002;// 消息协议设置长度与可读长度不符!
    public static final int GET_ACTION_EMPTY = -10003;// 未取得有效的action
    public static final int TYPE_NOT_EXIST = -10004;// 操作类型不存在
    public static final int SEND_MESSAGE_IS_EMPTY = -10005;// 发出的Message不能为空
    public static final int MESSAGE_BODY_TRANSFER_ERROR = -10006;// 消息内容转化出现错误，请检查消息体格式!

    // spring系统相关
    public static final int GET_BEAN_ERROR = -11001;// 获取处理器出现错误。
    public static final int GET_BEAN_EMPTY = -11002;// 未取得有效的处理器，请检查传入action。
    public static final int METHOD_NOT_FOUND = -11003;// 未取得有效的处理方法，请检查传入action。
    public static final int METHOD_PARAM_NOT_FOUND = -11004;// 未取得有效的参数，请检查传入数据参数名。
    public static final int SYS_OPERATION_CODE_NOT_EXIST = -11005;// 系统操作码不存在。
    public static final int METHOD_PARAM_NUM_NOT_MATCH = -11006;// 处理方法的参数数量与解析参数数量不匹配，请通知相应的管理员。
    public static final int METHOD_PARAM_TYPE_ERROR = -11007;// 未取得有效的参数，请检查各参数类型。
    public static final int CLASS_TRANSFER_ERROR = -11008;// 类型转换出现错误，请检查数据类型。
    public static final int PACKAGE_CLASS_TRANSFER_ERROR = -11009;// 未获得此基本类型的包装类。

    // 交易商信息
    public static final int USER_OR_PASSWORD_EMPTY = -12001;// 用户名或密码不能为空
    public static final int USER_OR_PASSWORD_ERROR = -12002;// 用户名、密码错误

    // grpc-pool
    public static final int GET_GRPC_CLIENT_ERROR = -13001;// 获取远程调用连接出现错误

}
