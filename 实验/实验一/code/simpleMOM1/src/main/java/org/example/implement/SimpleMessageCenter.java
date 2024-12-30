package org.example.implement;

import org.example.interfaces.Message;
import org.example.interfaces.MessageCenter;
import org.example.interfaces.MessageQueue;
import org.example.exception.MessageQueueException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 简单的消息中心类，实现了 MessageCenter 接口
 * @author: xyc
 * @date: 2023-04-25 16:20
 */
// 简单的消息中心类，实现了 MessageCenter 接口
public enum SimpleMessageCenter implements MessageCenter {
    INSTANCE;
    
    private final List<MessageQueue> queues = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void registerQueue(MessageQueue queue) {
        if (queue != null) {
            queues.add(queue);
        }
    }

    @Override
    public void unregisterQueue(MessageQueue queue) {
        queues.remove(queue);
    }

    @Override
    public void broadcast(Message message) throws MessageQueueException {
        if (message == null) {
            throw new MessageQueueException("Cannot broadcast null message");
        }
        
        for (MessageQueue queue : queues) {
            try {
                queue.enqueue(message);
            } catch (Exception e) {
                throw new MessageQueueException("Failed to broadcast message", e);
            }
        }
    }
}