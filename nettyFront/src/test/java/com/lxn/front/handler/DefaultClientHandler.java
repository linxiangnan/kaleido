package com.lxn.front.handler;

import com.lxn.front.constant.Constant;
import com.lxn.front.model.Message;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lin Xiangnan on 2017/2/20.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class DefaultClientHandler extends ChannelInboundHandlerAdapter {

    private static int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接成功。");

        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南");
//        stringBuilder.append("林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南林向南");

        List<String> strList = new ArrayList<>();
        strList.add("林向南1");
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("code", 1);
//        objectMap.put("code", 2222);
        objectMap.put("netty_action", "echo.echo");
        objectMap.put("name", "林向南");
        objectMap.put("password", "111111");
        objectMap.put("members", strList);
//
        Message message = new Message(Constant.MESSAGE_TYPE, 10, objectMap);
//        Message message = new Message(Constant.SYSTEM_TYPE, 10, objectMap);

        ctx.writeAndFlush(message).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        count++;
        System.out.println("读取数据:" + count);
        System.out.println(msg.toString());

//        Message message = (Message) msg;
//        Map map = message.getBody();
//        System.out.println(map.get("password"));
//        map.put("password", Long.parseLong(map.get("password").toString())+1);
//        message.setBody(map);

//        List<String> strList = new ArrayList<>();
//        strList.add("林向南1");
//        strList.add("林向南2");
//        strList.add("林向南3");
//        strList.add("林向南4");
//        strList.add("林向南5");
//        Map<String, Object> objectMap = new HashMap<>();
//        objectMap.put("nettyAction","normal_action.print_all_members_by_name");
//        objectMap.put("name","林向南1");
//        objectMap.put("password",);
//        objectMap.put("members",strList);
//
//        Message message = new Message(Constant.MESSAGE_TYPE, objectMap);
//        ctx.write(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        Thread.sleep(1000);
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开连接。");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("出现错误。");
    }

}
