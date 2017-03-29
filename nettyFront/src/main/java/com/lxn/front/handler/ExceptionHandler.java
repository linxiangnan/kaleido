package com.lxn.front.handler;

import com.lxn.front.exception.ApiException;
import com.lxn.front.model.ExceptionMessage;
import com.lxn.front.model.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Lin Xiangnan on 2017/3/3.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Component
@ChannelHandler.Sharable
public class ExceptionHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private Logger logger;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        String message = null;
        try {
            Message messageReturn;
            if (cause instanceof ApiException) {
                ApiException apiException = (ApiException) cause;
                message = apiException.getMessage();
                messageReturn = new ExceptionMessage(apiException.getMessageId(), apiException.getCode(), message);
            } else if (cause instanceof InvocationTargetException) {
                InvocationTargetException exception = (InvocationTargetException) cause;
                message = exception.getTargetException().getMessage();
                messageReturn = new ExceptionMessage(message);
            } else {
                message = cause.getMessage();
                messageReturn = new ExceptionMessage(cause.getMessage());
            }
            ctx.writeAndFlush(messageReturn);
            logger.info("【消息通信】——通道ID："+ctx.channel().id().toString()+"，发送错误信息：[" + messageReturn.toString() + "]。--------------------");
        } finally {
            logger.error("【消息通信】——通道ID："+ctx.channel().id().toString()+"，消息通信出现错误。错误原因：" + message + "。-----------------------------");
        }
    }

}
