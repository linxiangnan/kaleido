package com.lxn.front.component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by Lin Xiangnan on 2017/3/14.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class GrpcClient {

    private final ManagedChannel channel;

    public GrpcClient(String host, int port) {

        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();

    }

    public ManagedChannel getChannel() {
        return channel;
    }

}
