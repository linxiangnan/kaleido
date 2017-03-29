package com.lxn.front.business;

import com.lxn.front.application.AppContext;
import com.lxn.front.component.DispatcherAction;
import com.lxn.front.component.RpcDispatcher;
import com.lxn.front.constant.Constant;
import com.lxn.front.constant.ErrorCode;
import com.lxn.front.exception.ApiException;
import com.lxn.front.model.Message;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Lin Xiangnan on 2017/3/2.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class BusinessTask implements Callable<Message> {

    private Logger logger;

    private Message inputMessage;

        private DispatcherAction dispatcherAction;
//    private RpcDispatcher rpcDispatcher;

    public BusinessTask(Message inputMessage) {
        this.inputMessage = inputMessage;
        this.logger = (Logger) AppContext.getBean(Constant.COMPONENT_LOGGER_NAME);
        this.dispatcherAction = (DispatcherAction) AppContext.getBean("dispatcherAction");
//        this.rpcDispatcher = (RpcDispatcher) AppContext.getBean(Constant.COMPONENT_RPC_DISPATCHER_NAME);
    }

    @Override
    public Message call() throws Exception {

        logger.info("【业务处理】————业务线程ID：" + Thread.currentThread().getId() + "，MessageId：" + inputMessage.getMessageId() + "，业务流程处理中。。。");

        // 根据消息方式调用相应的RPC服务

        Message messageReturn;

        Map body = inputMessage.getBody();

        String action = (String) body.get(Constant.MESSAGE_ACTION_KEY);
        if (StringUtils.isEmpty(action)) {
            throw new ApiException(ErrorCode.GET_ACTION_EMPTY, "未获取到有效的action。");
        }

        String[] methodParams = action.split(Constant.MESSAGE_SPLIT_KEYWORD);
        if (methodParams.length != 2) {
            throw new ApiException(ErrorCode.GET_ACTION_EMPTY, "未获取到有效的action。");
        }
        String className = methodParams[0];
        String methodName = methodParams[1];

        messageReturn = dispatcherAction.callAction(inputMessage.getMessageId(), className, methodName, body);
//        messageReturn = rpcDispatcher.callRpc(inputMessage.getMessageId(), className, methodName, body);

        logger.info("【业务处理】————业务线程ID：" + Thread.currentThread().getId() + "，MessageId：" + inputMessage.getMessageId() + "，业务流程处理完成。-----------");
        return messageReturn;
    }
}
