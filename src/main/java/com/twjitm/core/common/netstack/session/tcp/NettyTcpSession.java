package com.twjitm.core.common.netstack.session.tcp;

import com.twjitm.core.common.netstack.entity.AbstractNettyNetMessage;
import com.twjitm.core.common.netstack.sender.NetTcpMessageSender;
import com.twjitm.core.common.netstack.session.NettySession;
import com.twjitm.core.common.process.tcp.NettyTcpNetProtoMessageProcess;
import com.twjitm.core.common.update.IUpdatable;
import com.twjitm.core.spring.SpringServiceManager;
import io.netty.channel.Channel;

/**
 * @author twjitm - [Created on 2018-07-24 15:59]
 * @jdk java version "1.8.0_77"
 * tcpЭ��Ự����
 */
public class NettyTcpSession extends NettySession implements IUpdatable {
    /**
     * session id
     */
    private long sessionId;
    /**
     * ��Ϣ����
     */
    private NettyTcpNetProtoMessageProcess netProtoMessageProcess;
    /**
     * ��Ϣ����
     */
    private NetTcpMessageSender netTcpMessageSender;

    boolean netMessageProcessSwitch=true;


    public NettyTcpSession(Channel channel) {
        super(channel);
        sessionId = SpringServiceManager.springLoadService.getLongIdGenerator().generateId();
        netProtoMessageProcess = new NettyTcpNetProtoMessageProcess(this);
        netTcpMessageSender = new NetTcpMessageSender(this);


    }
    /**
     * ������Ϣ�����л���
     * @param switchFlag
     */
    public void processNetMessage(boolean switchFlag){
        if(netMessageProcessSwitch || switchFlag){
            netProtoMessageProcess.update();
        }
    }

    /**
     * ���һ����Ϣ����
     *
     * @param abstractNetMessage
     */
    public void addNetMessage(AbstractNettyNetMessage abstractNetMessage) {
        this.netProtoMessageProcess.addNetMessage(abstractNetMessage);
        this.processNetMessage(true);
    }

    public void close() {

    }

    @Override
    public boolean update() {
        processNetMessage(false);
        return false;
    }

    public long getSessionId() {
        return sessionId;
    }

    public NettyTcpNetProtoMessageProcess getNetProtoMessageProcess() {
        return netProtoMessageProcess;
    }

    public NetTcpMessageSender getNetTcpMessageSender() {
        return netTcpMessageSender;
    }

    public boolean isNetMessageProcessSwitch() {
        return netMessageProcessSwitch;
    }
}
