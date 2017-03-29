package com.lxn.front.component;

import com.lxn.front.codec.ByteBufToOwnMessageDecoder;
import com.lxn.front.codec.OwnMessageToByteEncoder;
import com.lxn.front.constant.Constant;
import com.lxn.front.handler.DefaultHandler;
import com.lxn.front.handler.ExceptionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lin Xiangnan on 2017/2/21.
 *
 * @author Lin Xiangnan
 * @func
 **/
@Component
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private DefaultHandler defaultHandler;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {

        sc.pipeline().addLast(
                new LengthFieldBasedFrameDecoder(
                        Constant.PROTO_MAX_FRAME_LENGTH,
                        Constant.PROTO_LENGTH_FIELD_OFFSET,
                        Constant.PROTO_LENGTH_FIELD_LENGTH,
                        Constant.PROTO_LENGTH_ADJUSTMENT,
                        Constant.PROTO_INITIAL_BYTES_TO_STRIP)
        );

        sc.pipeline().addLast(new ByteBufToOwnMessageDecoder());

//        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//        sc.pipeline().addLast(new DelimiterBasedFrameDecoder(Constant.PROTO_MAX_FRAME_LENGTH, delimiter));

//        sc.pipeline().addLast(new LineBasedFrameDecoder(Constant.PROTO_MAX_FRAME_LENGTH));

//        sc.pipeline().addLast(new SplitByteBufToOwnMessageDecoder());

        sc.pipeline().addLast(new OwnMessageToByteEncoder());

        sc.pipeline().addLast(defaultHandler);
//        sc.pipeline().addLast(EventLoopGroupManager.getDefaultHandlerGroup(), defaultHandler);

        sc.pipeline().addLast(exceptionHandler);

    }

//    public void stop(){
//        eventExecutorGroup.shutdownGracefully();
//    }

}
