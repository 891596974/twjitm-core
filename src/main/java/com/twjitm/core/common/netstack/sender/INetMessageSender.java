package com.twjitm.core.common.netstack.sender;

import com.twjitm.core.common.netstack.entity.AbstractNettyNetMessage;

/**
 * @author twjitm - [Created on 2018-07-24 16:23]
 * ��Ϣ������
 * @jdk java version "1.8.0_77"
 */
public interface INetMessageSender {

    /**
     * ������Ϣ
     *
     * @param abstractNettyNetMessage
     * @return
     */
    public boolean sendMessage(AbstractNettyNetMessage abstractNettyNetMessage);

    /**
     * �ر�
     */
    public void close();
}
