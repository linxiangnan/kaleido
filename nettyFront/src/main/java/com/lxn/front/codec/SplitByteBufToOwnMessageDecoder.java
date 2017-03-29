package com.lxn.front.codec;

import com.lxn.front.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class SplitByteBufToOwnMessageDecoder extends ByteToMessageDecoder {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        //注意在读的过程中，readIndex的指针也在移动
        byte type = in.readByte();

        Integer code = in.readInt();

        int actualLength = in.readableBytes();

        ByteBuf buf = in.readBytes(actualLength);
        byte[] bytes = new byte[actualLength];
        buf.readBytes(bytes);
        buf.release();
        Map body = objectMapper.readValue(bytes, Map.class);
        Message message = new Message(type, code, body);
        out.add(message);

    }
}
