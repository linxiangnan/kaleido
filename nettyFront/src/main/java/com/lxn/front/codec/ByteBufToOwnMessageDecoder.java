package com.lxn.front.codec;

import com.lxn.front.constant.Constant;
import com.lxn.front.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxn.front.exception.ApiException;
import com.lxn.front.constant.ErrorCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Map;

/**
 * Created by Lin Xiangnan on 2017/2/20.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class ByteBufToOwnMessageDecoder extends ByteToMessageDecoder {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < Constant.PROTO_HEAD_SIZE) {
            throw new ApiException(ErrorCode.MESSAGE_LESS_THAN_HEAD_SIZE, "可读信息段小于头部信息!");
        }

        //注意在读的过程中，readIndex的指针也在移动
        byte type = in.readByte();

        Integer messageId = in.readInt();

        int setLength = in.readInt();

        int actualLength = in.readableBytes();

        if (actualLength != setLength) {
            throw new ApiException(messageId, ErrorCode.MESSAGE_ACTUAL_LENGTH_NOT_EQUAL_SET_LENGTH, "消息协议设置长度与可读长度不符!");
        }
        ByteBuf buf = in.readBytes(actualLength);
        byte[] bytes = new byte[actualLength];
        buf.readBytes(bytes);
        buf.release();
        Map body;
        try {
            body = objectMapper.readValue(bytes, Map.class);
        }catch (Exception e){
            throw new ApiException(messageId, ErrorCode.MESSAGE_BODY_TRANSFER_ERROR, "消息内容转化出现错误，请检查消息体格式!");
        }
        Message message = new Message(type, messageId, body);
        out.add(message);

    }
}
