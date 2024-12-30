package org.example.interfaces;

// 消息队列接口，表示一个消息队列
public interface MessageQueue {
    void enqueue(Message message) throws InterruptedException;
    Message dequeue() throws InterruptedException;
    void addListener(MessageListener listener);
    void removeListener(MessageListener listener);
}
