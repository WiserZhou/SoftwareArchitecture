package com.example.logistics.implement;

import com.example.logistics.LogisticsInfo;
import com.example.logistics.interfaces.MessageListener;
import com.example.logistics.interfaces.MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleMessageQueue implements MessageQueue {

    private int capacity;
    private BlockingQueue<LogisticsInfo> messages;
    private List<MessageListener> listeners = new ArrayList<>();

    public SimpleMessageQueue(int capacity) {
        this.capacity = capacity;
        this.messages = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public void enqueue(LogisticsInfo logisticsInfo) throws InterruptedException {
        messages.put(logisticsInfo);
        notifyListeners(logisticsInfo);
    }

    @Override
    public LogisticsInfo dequeue() throws InterruptedException {
        return messages.take();
    }

    @Override
    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(MessageListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(LogisticsInfo logisticsInfo) throws InterruptedException {
        for (MessageListener listener : listeners) {
            listener.onMessageChanged(logisticsInfo);
        }
    }

    @Override
    public void updateLogisticsInfo(LogisticsInfo logisticsInfo) throws InterruptedException {
        // 更新队列中的物流信息
        messages.removeIf(info -> info.getPackageId().equals(logisticsInfo.getPackageId()));
        messages.offer(logisticsInfo);
        notifyListeners(logisticsInfo);
    }
}