package com.lxn.front.exception;

import org.springframework.util.StringUtils;

/**
 * Created by Lin Xiangnan on 2017/2/22.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class ApiException extends Exception {

    private Integer messageId = -1;
    private Integer code = -1;
    private String message = "出现未知异常！";

    public ApiException(Integer messageId, Integer code, String message) {
        super(message);
        if (messageId != null) {
            this.messageId = messageId;
        }
        if (code != null) {
            this.code = code;
        }
        if (!StringUtils.isEmpty(message)) {
            this.message = message;
        }
    }

    public ApiException(Integer code, String message) {
        super(message);
        if (code != null) {
            this.code = code;
        }
        if (!StringUtils.isEmpty(message)) {
            this.message = message;
        }
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
