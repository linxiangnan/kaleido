package com.lxn.front.model;

import com.lxn.front.constant.Constant;

/**
 * Created by Lin Xiangnan on 2017/2/22.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class ExceptionMessage extends Message {

    public ExceptionMessage(String message) {
        super(Constant.MESSAGE_TYPE, Constant.UN_HANDLE_ERROR_MESSAGE_ID, Constant.UN_HANDLE_ERROR_CODE, message);
    }

    public ExceptionMessage(int code, String message) {
        super(Constant.MESSAGE_TYPE, Constant.UN_HANDLE_ERROR_MESSAGE_ID, code, message);
    }

    public ExceptionMessage(int messageId, int code, String message) {
        super(Constant.MESSAGE_TYPE, messageId, code, message);
    }


}
