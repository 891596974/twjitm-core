package com.twjitm.core.common.netstack.sender;

import com.twjitm.core.common.netstack.entity.AbstractNettyNetMessage;
import com.twjitm.core.common.netstack.session.NettySession;
import com.twjitm.core.utils.logs.LoggerUtils;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

/**
 * @author EGLS0807 - [Created on 2018-07-24 16:32]
 * @jdk java version "1.8.0_77"
 * TCP��Ϣ������
 */
public class NetTcpMessageSender implements INetMessageSender {
    private final NettySession nettySession;
    private Logger logger = LoggerUtils.getLogger(this.getClass());

    public NetTcpMessageSender(NettySession nettySession) {
        this.nettySession = nettySession;
    }

    @Override
    public boolean sendMessage(AbstractNettyNetMessage abstractNettyNetMessage) {
        try {
            nettySession.write(abstractNettyNetMessage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return true;
    }

    @Override
    public void close() {
        Channel channel = nettySession.channel;
        if (channel.isActive()) {
            channel.close();
        } else {
            channel.close();

        }
    }
}
