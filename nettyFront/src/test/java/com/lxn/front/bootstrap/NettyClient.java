package com.lxn.front.bootstrap;

import com.lxn.front.codec.ByteBufToOwnMessageDecoder;
import com.lxn.front.codec.OwnMessageToByteEncoder;
import com.lxn.front.constant.Constant;
import com.lxn.front.handler.DefaultClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by Lin Xiangnan on 2017/2/20.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class NettyClient {

    private static Channel channel;

    private void run() throws InterruptedException {

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ChannelInitializer channelInitializer = new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel sc) {

                    sc.pipeline().addLast(
                            new LengthFieldBasedFrameDecoder(
                                    Constant.PROTO_MAX_FRAME_LENGTH,
                                    Constant.PROTO_LENGTH_FIELD_OFFSET,
                                    Constant.PROTO_LENGTH_FIELD_LENGTH,
                                    Constant.PROTO_LENGTH_ADJUSTMENT,
                                    Constant.PROTO_INITIAL_BYTES_TO_STRIP)
                    );

                    sc.pipeline().addLast(new ByteBufToOwnMessageDecoder());

//                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//                    sc.pipeline().addLast(new DelimiterBasedFrameDecoder(Constant.PROTO_MAX_FRAME_LENGTH, delimiter));

//                    sc.pipeline().addLast(new LineBasedFrameDecoder(Constant.PROTO_MAX_FRAME_LENGTH));

//                    sc.pipeline().addLast(new SplitByteBufToOwnMessageDecoder());

                    sc.pipeline().addLast(new OwnMessageToByteEncoder());

                    sc.pipeline().addLast(new DefaultClientHandler());
                }
            };

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(channelInitializer);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);

            ChannelFuture channelFuture = bootstrap.connect("localhost", 18081).sync();

            Channel channel = channelFuture.channel();
            NettyClient.channel = channel;
            channel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyClient client = new NettyClient();
        client.run();

    }

}
