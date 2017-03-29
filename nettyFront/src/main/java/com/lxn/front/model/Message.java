package com.lxn.front.model;

import com.lxn.front.constant.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lin Xiangnan on 2017/1/16.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class Message {

    private byte type;  // 操作码
    private int messageId;  // 消息Id
    private Map body;   // 消息内容

    public Message(byte type, int messageId, Map body) {
        this.type = type;
        this.messageId = messageId;
        if(!body.containsKey(Constant.MESSAGE_CODE_KEY)){
            body.put(Constant.MESSAGE_CODE_KEY, Constant.SUCCESS_CODE);
        }
        this.body = body;
    }

    public Message(byte type, int messageId, int code, String message) {
        this.type = type;
        this.messageId = messageId;
        Map bodyResult = new HashMap();
        bodyResult.put(Constant.MESSAGE_CODE_KEY, code);
        bodyResult.put(Constant.MESSAGE_EXCEPTION_INFO_KEY, message);
        this.body = bodyResult;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Map getBody() {
        return body;
    }

    public void setBody(Map body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", messageId=" + messageId +
                ", body=[" + body +
                "]}";
    }
}
