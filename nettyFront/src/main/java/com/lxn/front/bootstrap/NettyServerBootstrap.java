package com.lxn.front.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lin Xiangnan on 2017/2/20.
 *
 * @author Lin Xiangnan
 * @func 启动netty——server，并初始化spring配置
 **/
public class NettyServerBootstrap {

    @Autowired
    private Logger logger;

    private ChannelInitializer channelInitializer;

    private int port;

    public NettyServerBootstrap(int port) {
        this.port = port;
    }

    public NettyServerBootstrap(ChannelInitializer channelInitializer, int port) {
        this.channelInitializer = channelInitializer;
        this.port = port;
    }

    public void start() throws InterruptedException {

//        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(EventLoopGroupManager.getBossGroup(), EventLoopGroupManager.getWorkerGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(channelInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        // 绑定端口，开始接收进来的数据
        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        Channel channel = channelFuture.channel();
        ChannelGroupManager.addChannel(channel);
        logger.info("【netty服务器】：netty服务器已启动完成，绑定接口：" + port + "。----------------------");

    }

    public void stop() {
        try {
            ChannelGroupManager.getChannelGroup().close().sync();
        } catch (InterruptedException e) {
            logger.error("【netty服务器】：ChannelGroup关闭出现错误，执行异常关闭。。。");
            return;
        } finally {
            EventLoopGroupManager.shutDownGroups();
        }
        logger.info("【netty服务器】：netty服务器执行优雅地异步关闭操作。。。");
    }


}
