package org.example.interfaces;

import org.example.exception.MessageQueueException;

// 消息中心接口，表示一个消息中心
public interface MessageCenter {
    void registerQueue(MessageQueue queue);
    void unregisterQueue(MessageQueue queue);
    void broadcast(Message message) throws InterruptedException, MessageQueueException;
}

