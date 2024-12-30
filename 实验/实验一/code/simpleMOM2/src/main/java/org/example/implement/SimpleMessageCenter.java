package org.example.implement;


import org.example.interfaces.Message;
import org.example.interfaces.MessageCenter;
import org.example.interfaces.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 简单的消息中心类，实现了 MessageCenter 接口
 * @author: xyc
 * @date: 2023-04-25 16:20
 */
public class SimpleMessageCenter implements MessageCenter {
    private List<MessageQueue> queues = new ArrayList<>();
    private volatile static SimpleMessageCenter instance;
    private SimpleMessageCenter() {}

    public static SimpleMessageCenter getInstance() {
        if (instance == null) {
            synchronized (SimpleMessageCenter.class) {
                if (instance == null) {
                    instance = new SimpleMessageCenter();
                }
            }
        }
        return instance;
    }

    @Override
    public void registerQueue(MessageQueue queue) {
        queues.add(queue);
    }

    @Override
    public void unregisterQueue(MessageQueue queue) {
        queues.remove(queue);
    }
    
    // 修改部分, 修改内容：将 broadcast 方法的参数 queue 修改为 queues
    @Override
    public void broadcast(Message message, MessageQueue queue) throws InterruptedException {
        queue.enqueue(message);
    }
}