package com.lxn.front.bootstrap;

import com.lxn.front.constant.Constant;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by Lin Xiangnan on 2017/3/10.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class EventLoopGroupManager {

    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    private static EventExecutorGroup defaultHandlerGroup = new DefaultEventExecutorGroup(Constant.NETTY_HANDLER_THREAD_NUM);
    private static EventExecutorGroup businessThreadGroup = new DefaultEventExecutorGroup(Constant.SERVICE_THREAD_NUM);

    public static EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public static EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public static EventExecutorGroup getDefaultHandlerGroup() {
        return defaultHandlerGroup;
    }

    public static EventExecutorGroup getBusinessThreadGroup() {
        return businessThreadGroup;
    }

    static void shutDownGroups() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (defaultHandlerGroup != null) {
            defaultHandlerGroup.shutdownGracefully();
        }
        if (businessThreadGroup != null) {
            businessThreadGroup.shutdownGracefully();
        }
    }

}
