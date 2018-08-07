package com.twjitm.core.spring;

import com.twjitm.core.common.factory.MessageRegistryFactory;
import com.twjitm.core.common.factory.NettyTcpMessageFactory;
import com.twjitm.core.common.netstack.builder.NettyTcpSessionBuilder;
import com.twjitm.core.common.netstack.coder.decode.http.INettyNetProtoBuffHttpToMessageDecoderFactory;
import com.twjitm.core.common.netstack.coder.decode.tcp.INettyNetProtoBuffTCPToMessageDecoderFactory;
import com.twjitm.core.common.netstack.coder.decode.udp.INettyNetProtoBuffUDPToMessageDecoderFactory;
import com.twjitm.core.common.netstack.coder.encode.http.INettyNetProtoBufHttpMessageEncoderFactory;
import com.twjitm.core.common.netstack.coder.encode.tcp.INettyNetProtoBufTcpMessageEncoderFactory;
import com.twjitm.core.common.netstack.coder.encode.udp.INettyNetProtoBufUdpMessageEncoderFactory;
import com.twjitm.core.common.netstack.pipeline.INettyServerPipeLine;
import com.twjitm.core.common.netstack.pipeline.NettyTcpServerPipeLineImpl;
import com.twjitm.core.common.process.INetProtoMessageProcess;
import com.twjitm.core.common.process.NetProtoMessageProcess;
import com.twjitm.core.common.process.NettyNetMessageProcessLogic;
import com.twjitm.core.common.process.tcp.NettyTcpMessageQueueExecutorProcessor;
import com.twjitm.core.common.service.INettyChannleOperationService;
import com.twjitm.core.common.service.IService;
import com.twjitm.core.common.service.Impl.NettyChannelOperationServiceImpl;
import com.twjitm.core.service.dispatcher.IDispatcherService;
import com.twjitm.core.service.test.TestService;
import com.twjitm.core.service.user.UserService;
import com.twjitm.core.utils.uuid.LongIdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author �Ľ�
 * @date 2018/4/16
 * ͨ��spring bean����
 */
@Service
public class SpringLoadServiceImpl implements IService {
    @Resource
    private TestService testService;
    @Resource
    private UserService userService;
    @Resource
    private IDispatcherService dispatcherService;
    @Resource
    private MessageRegistryFactory messageRegistryFactory;


    /**http������*/
    @Resource
    private INettyNetProtoBufHttpMessageEncoderFactory nettyNetProtoBufHttpMessageEncoderFactory;

    /**http������*/
     @Resource
    private INettyNetProtoBuffHttpToMessageDecoderFactory nettyNetProtoBuffHttpToMessageDecoderFactory;

     /**tcpЭ�������*/
     @Resource
     private INettyNetProtoBufTcpMessageEncoderFactory nettyNetProtoBufTcpMessageEncoderFactory;

    /**tcpЭ�������*/
    @Resource
    private INettyNetProtoBuffTCPToMessageDecoderFactory nettyNetProtoBuffTCPToMessageDecoderFactory;

    /**updЭ�����������**/
    @Resource
    private INettyNetProtoBufUdpMessageEncoderFactory nettyNetProtoBufUdpMessageEncoderFactory;
    /**udpЭ�������**/
    @Resource
    private INettyNetProtoBuffUDPToMessageDecoderFactory nettyNetProtoBuffUDPToMessageDecoderFactory;

    /**ԭ��id������**/
    @Resource
    private LongIdGenerator longIdGenerator;
    /**��Ϣ����bean**/
    @Resource
    private NettyNetMessageProcessLogic nettyNetMessageProcessLogic;
    @Resource
    NetProtoMessageProcess netProtoMessageProcess;


    @Resource
    private NettyTcpSessionBuilder nettyTcpSessionBuilder;
    @Resource
    private NettyChannelOperationServiceImpl netTcpSessionLoopUpService;
    @Resource
    private NettyTcpMessageFactory nettyTcpMessageFactory;
    @Resource
    private NettyTcpServerPipeLineImpl nettyTcpServerPipeLine;
    @Resource
    NettyTcpMessageQueueExecutorProcessor nettyTcpMessageQueueExecutorProcessor;



    public TestService getTestService() {
        return testService;
    }

    public UserService getUserService() {
        return userService;
    }

    public IDispatcherService getDispatcherService() {
        return dispatcherService;
    }

    public MessageRegistryFactory getMessageRegistryFactory() {
        return messageRegistryFactory;
    }

    public INettyNetProtoBufHttpMessageEncoderFactory getNettyNetProtoBufHttpMessageEncoderFactory() {
        return nettyNetProtoBufHttpMessageEncoderFactory;
    }

    public INettyNetProtoBuffHttpToMessageDecoderFactory getNettyNetProtoBuffHttpToMessageDecoderFactory() {
        return nettyNetProtoBuffHttpToMessageDecoderFactory;
    }

    public INettyNetProtoBufTcpMessageEncoderFactory getNettyNetProtoBufTcpMessageEncoderFactory() {
        return nettyNetProtoBufTcpMessageEncoderFactory;
    }

    public INettyNetProtoBuffTCPToMessageDecoderFactory getNettyNetProtoBuffTCPToMessageDecoderFactory() {
        return nettyNetProtoBuffTCPToMessageDecoderFactory;
    }

    public LongIdGenerator getLongIdGenerator() {
        return longIdGenerator;
    }

    public NettyNetMessageProcessLogic getNettyNetMessageProcessLogic() {
        return nettyNetMessageProcessLogic;
    }

    public NettyTcpSessionBuilder getNettyTcpSessionBuilder() {
        return nettyTcpSessionBuilder;
    }

    public INettyChannleOperationService getNetTcpSessionLoopUpService() {
        return netTcpSessionLoopUpService;
    }

    public NettyTcpMessageFactory getNettyTcpMessageFactory() {
        return nettyTcpMessageFactory;
    }

    public INettyNetProtoBufUdpMessageEncoderFactory getNettyNetProtoBufUdpMessageEncoderFactory() {
        return nettyNetProtoBufUdpMessageEncoderFactory;
    }
    public INettyNetProtoBuffUDPToMessageDecoderFactory getNettyNetProtoBuffUDPToMessageDecoderFactory() {
        return nettyNetProtoBuffUDPToMessageDecoderFactory;
    }

    public NettyTcpServerPipeLineImpl getNettyTcpServerPipeLine() {
        return nettyTcpServerPipeLine;
    }

    public INetProtoMessageProcess getNetProtoMessageProcess() {
        return netProtoMessageProcess;
    }

    public NettyTcpMessageQueueExecutorProcessor getNettyTcpMessageQueueExecutorProcessor() {
        return nettyTcpMessageQueueExecutorProcessor;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public void startup() throws Exception {
        netTcpSessionLoopUpService.startup();
        nettyTcpMessageQueueExecutorProcessor.start();
    }

    @Override
    public void shutdown() throws Exception {
        netTcpSessionLoopUpService.shutdown();
        nettyTcpMessageQueueExecutorProcessor.stop();
    }


}
