package org.example.interfaces;

import org.example.exception.MessageQueueException;

public interface MessageQueue {
    void enqueue(Message message) throws MessageQueueException;
    Message dequeue() throws MessageQueueException;
    void addListener(MessageListener listener);
    void removeListener(MessageListener listener);
}
