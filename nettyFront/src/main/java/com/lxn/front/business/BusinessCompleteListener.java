package com.lxn.front.business;

import com.lxn.front.application.AppContext;
import com.lxn.front.constant.Constant;
import com.lxn.front.exception.ApiException;
import com.lxn.front.model.ExceptionMessage;
import com.lxn.front.model.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Lin Xiangnan on 2017/3/2.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class BusinessCompleteListener implements FutureListener<Message> {

    private ChannelHandlerContext ctx;
    private Message inputMessage;
    private Logger logger;

    public BusinessCompleteListener(ChannelHandlerContext ctx, Message inputMessage) {
        this.ctx = ctx;
        this.inputMessage = inputMessage;
        this.logger = (Logger) AppContext.getBean(Constant.COMPONENT_LOGGER_NAME);
    }


    @Override
    public void operationComplete(Future<Message> future) throws Exception {
        Channel channel = ctx.channel();
        Message messageReturn;
        try {
            messageReturn = future.get();
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                ApiException ex = (ApiException) e.getCause();
                messageReturn = new ExceptionMessage(inputMessage.getMessageId(), ex.getCode(), ex.getMessage());
            } else if (e.getCause() instanceof InvocationTargetException) {
                InvocationTargetException exception = (InvocationTargetException) e.getCause();
                if (exception.getTargetException() instanceof ApiException) {
                    ApiException ex = (ApiException) exception.getTargetException();
                    messageReturn = new ExceptionMessage(inputMessage.getMessageId(), ex.getCode(), ex.getMessage());
                } else {
                    messageReturn = new ExceptionMessage(inputMessage.getMessageId(), Constant.UN_HANDLE_ERROR_CODE, exception.getTargetException().getMessage());
                }
            } else {
                messageReturn = new ExceptionMessage(inputMessage.getMessageId(), Constant.UN_HANDLE_ERROR_CODE, e.getCause().getMessage());
            }

            channel.writeAndFlush(messageReturn).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            logger.error("【业务处理】——业务处理出现错误，错误信息：[" + messageReturn.getBody().get(Constant.MESSAGE_EXCEPTION_INFO_KEY) + "]。");
            logger.error("【消息通信】——通道ID：" + channel.id().toString() + "，MessageId："+inputMessage.getMessageId()+"，发送消息：[" + messageReturn.toString() + "]。");
            return;
        }
        channel.writeAndFlush(messageReturn).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        logger.info("【消息通信】——通道ID：" + channel.id().toString() + "，MessageId："+inputMessage.getMessageId()+"，业务处理完成，发送消息：[" + messageReturn.toString() + "]。");
    }
}
