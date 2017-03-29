package com.lxn.front.component;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lin Xiangnan on 2017/3/14.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class GrpcClientFactory extends BasePooledObjectFactory<GrpcClient> {

    @Autowired
    private Logger logger;

    private static GrpcClientFactory grpcClientFactory;
    private static GenericObjectPoolConfig poolConfig;

    private static GenericObjectPool<GrpcClient> grpcClientPool;

    private static String host;
    private static int port;

    private GrpcClientFactory() {
    }

    public GrpcClientFactory(String host, int port, int maxTotal, int minIdle, int maxIdle) {
        GrpcClientFactory.host = host;
        GrpcClientFactory.port = port;
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxTotal); // 池中的最大连接数
        poolConfig.setMinIdle(minIdle); // 最少的空闲连接数
        poolConfig.setMaxIdle(maxIdle); // 最多的空闲连接数
        poolConfig.setMaxWaitMillis(-1); // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
        poolConfig.setLifo(true); // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
        poolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L); // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟
        poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞,默认为true
        GrpcClientFactory.grpcClientFactory = new GrpcClientFactory();
        GrpcClientFactory.poolConfig = poolConfig;
    }

    public void init(){
        grpcClientPool = new GenericObjectPool<>(grpcClientFactory, poolConfig);
        logger.info("【GRPC连接池】——GRPC连接池初始化设置完成，预设连接地址：" + host + ":" + port + "。----------");
    }

    public GenericObjectPool<GrpcClient> getClientPool() throws Exception {
        return grpcClientPool;
    }

    @Override
    public GrpcClient create() throws Exception {
        return new GrpcClient(host, port);
    }

    @Override
    public PooledObject<GrpcClient> wrap(GrpcClient client) {
        return new DefaultPooledObject<>(client);
    }

    public void shutdown() {
        grpcClientPool.close();
    }
}
