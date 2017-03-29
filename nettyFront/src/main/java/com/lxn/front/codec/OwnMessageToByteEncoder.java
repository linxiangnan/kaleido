package com.lxn.front.codec;

import com.lxn.front.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxn.front.exception.ApiException;
import com.lxn.front.constant.ErrorCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Map;

/**
 * Created by Lin Xiangnan on 2017/2/20.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class OwnMessageToByteEncoder extends MessageToByteEncoder<Message> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        if (null == msg) {
            throw new ApiException(ErrorCode.SEND_MESSAGE_IS_EMPTY, "发出的Message不能为空!");
        }
        Map body = msg.getBody();
        byte[] objBytes = objectMapper.writeValueAsBytes(body);
        out.writeByte(msg.getType());
        out.writeInt(msg.getMessageId());
        out.writeInt(objBytes.length);
        out.writeBytes(objBytes);

    }

}
