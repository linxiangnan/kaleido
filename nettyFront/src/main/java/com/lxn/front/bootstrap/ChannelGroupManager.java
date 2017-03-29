package com.lxn.front.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by Lin Xiangnan on 2017/3/10.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class ChannelGroupManager {

    private static ChannelGroup channelGroup = new DefaultChannelGroup("ALL_CHANNELS", GlobalEventExecutor.INSTANCE);

    public static ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    public static void addChannel(Channel channel) {
        channelGroup.add(channel);
    }

}
