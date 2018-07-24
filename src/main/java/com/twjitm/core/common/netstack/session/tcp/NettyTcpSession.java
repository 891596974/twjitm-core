package com.twjitm.core.common.netstack.session.tcp;

import com.twjitm.core.common.netstack.entity.AbstractNettyNetMessage;
import com.twjitm.core.common.netstack.sender.NetTcpMessageSender;
import com.twjitm.core.common.netstack.session.NettySession;
import com.twjitm.core.common.process.NetProtoMessageProcess;
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
    private NetProtoMessageProcess netProtoMessageProcess;
    /**
     * ��Ϣ����
     */
    private NetTcpMessageSender netTcpMessageSender;

    boolean netMessageProcessSwitch=true;


    public NettyTcpSession(Channel channel) {
        super(channel);
        sessionId = SpringServiceManager.springLoadManager.getLongIdGenerator().generateId();
        netProtoMessageProcess = new NetProtoMessageProcess(this);
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
    }

    public void close() {

    }

    @Override
    public boolean update() {
        processNetMessage(false);
        return false;
    }
}
