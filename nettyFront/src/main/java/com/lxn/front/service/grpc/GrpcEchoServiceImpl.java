//package com.lxn.front.service.grpc;
//
//import cn.boce.forward.proto.echo.EchoGrpc;
//import cn.boce.forward.proto.echo.EchoRequest;
//import com.lxn.front.annotation.MethodName;
//import com.lxn.front.annotation.ParamName;
//import com.lxn.front.component.GrpcClient;
//import com.lxn.front.component.GrpcClientFactory;
//import com.lxn.front.constant.ErrorCode;
//import com.lxn.front.exception.ApiException;
//import com.lxn.front.service.EchoService;
//import io.grpc.ManagedChannel;
//import org.apache.commons.pool2.impl.GenericObjectPool;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Lin Xiangnan on 2017/3/14.
// *
// * @author Lin Xiangnan
// * @func
// **/
//@Service(value = "grpc_echo")
//public class GrpcEchoServiceImpl implements EchoService {
//
//    @Autowired
//    private Logger logger;
//
//    @Autowired
//    private GrpcClientFactory grpcClientFactory;
//
//    @Override
//    @MethodName(value = "echo")
//    public Map echo(@ParamName(value = "name") String name) throws Exception {
//        GenericObjectPool<GrpcClient> clientPool = grpcClientFactory.getClientPool();
//        GrpcClient grpcClient = clientPool.borrowObject();
//        String nameReturn;
//        try {
//            ManagedChannel channel = grpcClient.getChannel();
//            EchoGrpc.EchoBlockingStub echoBlockingStub = EchoGrpc.newBlockingStub(channel);
//
//            EchoRequest echoRequest = EchoRequest.newBuilder().setName(name).build();
//            nameReturn = echoBlockingStub.echo(echoRequest).getMessage();
//        } catch (Exception e) {
//            throw new ApiException(ErrorCode.GET_GRPC_CLIENT_ERROR, "业务线程ID：" + Thread.currentThread().getId() + "，获取远程调用连接出现错误。");
//        } finally {
//            clientPool.returnObject(grpcClient);
//        }
//
//        Map result = new HashMap();
//        result.put("name", nameReturn);
//        logger.info("【RPC调用】——业务线程ID：" + Thread.currentThread().getId() + "，RPC标志："+grpcClient.toString()+"，RPC远程调用完成。--------");
//        return result;
//
//    }
//
//
//}
