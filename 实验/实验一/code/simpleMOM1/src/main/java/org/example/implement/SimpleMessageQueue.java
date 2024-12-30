package org.example.implement;


import org.example.exception.MessageQueueException;
import org.example.interfaces.Message;
import org.example.interfaces.MessageListener;
import org.example.interfaces.MessageQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 简单的消息队列类，实现了 MessageQueue 接口
 * @author: xyc
 * @date: 2023-04-25 16:18
 */
// 简单的消息队列类，实现了 MessageQueue 接口
public class SimpleMessageQueue implements MessageQueue {
    private final List<Message> messages = Collections.synchronizedList(new ArrayList<>());
    private final List<MessageListener> listeners = Collections.synchronizedList(new ArrayList<>());

    @Override
    public synchronized void enqueue(Message message) throws MessageQueueException {
        messages.add(message);
        notifyListeners();
    }

    @Override
    public synchronized Message dequeue() {
        if (messages.isEmpty()) {
            return null;
        }
        return messages.remove(0);
    }

    @Override
    public void addListener(MessageListener listener) {
        if(listener != null)
            listeners.add(listener);
    }

    @Override
    public void removeListener(MessageListener listener) {
        if(listener != null)
            listeners.remove(listener);
    }

    private void notifyListeners() throws MessageQueueException {
        for (MessageListener listener : listeners) {
            if (listener != null) {
                listener.onMessageChanged(this);
            }
        }
    }
}