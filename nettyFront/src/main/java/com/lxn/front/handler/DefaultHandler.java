package com.lxn.front.handler;

import com.lxn.front.application.AppContext;
import com.lxn.front.bootstrap.EventLoopGroupManager;
import com.lxn.front.business.BusinessCompleteListener;
import com.lxn.front.business.BusinessTask;
import com.lxn.front.constant.Constant;
import com.lxn.front.constant.ErrorCode;
import com.lxn.front.exception.ApiException;
import com.lxn.front.model.Message;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lin Xiangnan on 2017/2/20.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Component
@ChannelHandler.Sharable
public class DefaultHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private Logger logger;

    private static Set<Byte> typeSet;

    static {
        typeSet = new HashSet<>();
        typeSet.add(Constant.MESSAGE_TYPE);
        typeSet.add(Constant.SYSTEM_TYPE);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ChannelGroupManager.addChannel(ctx.channel());
        logger.info("【消息通信】——新建连接通道成功，通道ID：" + ctx.channel().id().toString() + "。时间：" + new Date().toString() + "--------------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Message message = (Message) msg;
        logger.info("【消息通信】——通道ID：" + ctx.channel().id().toString() + ",收到消息：[" + message.toString() + "]。");
        // 检查type类型（区分系统操作指令与正常请求）
        if (!typeSet.contains(message.getType())) {
            throw new ApiException(ErrorCode.TYPE_NOT_EXIST, "操作类型不存在。");
        }
        Map body = message.getBody();
        if (Constant.SYSTEM_TYPE.equals(message.getType())) {
            // 执行系统操作
            AppContext.operateApp((int) body.get(Constant.MESSAGE_CODE_KEY));
            Message messageReturn = new Message(Constant.MESSAGE_TYPE, message.getMessageId(), Constant.SUCCESS_CODE, "系统操作已成功！");
            ctx.writeAndFlush(messageReturn).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            logger.info("【消息通信】——通道ID：" + ctx.channel().id().toString() + ",系统操作处理完成，发送消息：[" + messageReturn.toString() + "]。");
        } else {
            // 执行普通消息操作
            Future<Message> future = EventLoopGroupManager.getBusinessThreadGroup().submit(new BusinessTask(message));
            future.addListener(new BusinessCompleteListener(ctx, message));
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("【消息通信】——关闭连接通道成功，通道ID：" + ctx.channel().id().toString() + "。时间：" + new Date().toString() + "-----------");
    }

}
