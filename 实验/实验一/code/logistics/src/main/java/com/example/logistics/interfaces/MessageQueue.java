package com.example.logistics.interfaces;

import com.example.logistics.LogisticsInfo;

// 消息队列接口，表示一个消息队列
public interface MessageQueue {
    void enqueue(LogisticsInfo logisticsInfo) throws InterruptedException;
    LogisticsInfo dequeue() throws InterruptedException;
    void addListener(MessageListener listener);
    void removeListener(MessageListener listener);

    void updateLogisticsInfo(LogisticsInfo logisticsInfo) throws InterruptedException;
}
