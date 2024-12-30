package com.example.logistics.interfaces;

// 消息中心接口，表示一个消息中心
public interface MessageCenter {
    void registerQueue(MessageQueue queue);
    void unregisterQueue(MessageQueue queue);

}

