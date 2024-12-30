package org.example.example;


import org.example.exception.MessageQueueException;
import org.example.factory.MessageFactory;
import org.example.interfaces.Message;
import org.example.interfaces.MessageCenter;
import org.example.interfaces.MessageListener;
import org.example.interfaces.MessageQueue;

/**
 * @description: 使用示例
 * @author: xyc
 * @date: 2023-04-25 16:28
 */
public class Example {
    public static void main(String[] args) throws InterruptedException, MessageQueueException {
        // 创建消息中心
        MessageCenter center = MessageFactory.createMessageCenter();

        // 创建两个消息队列并注册到消息中心
        MessageQueue queue1 = MessageFactory.createMessageQueue();
        MessageQueue queue2 = MessageFactory.createMessageQueue();
        center.registerQueue(queue1);
        center.registerQueue(queue2);

        // 创建两个消息监听器并注册到消息队列
        MessageListener listener1 = new MessageListener() {
            @Override
            public void onMessageChanged(MessageQueue queue) throws MessageQueueException {
                Message message = queue.dequeue();
                if(message != null)
                    System.out.println("Listener 1: " + message.getContent());
            }
        };
        queue1.addListener(listener1);

        MessageListener listener2 = new MessageListener() {
            @Override
            public void onMessageChanged(MessageQueue queue) throws MessageQueueException {
                Message message = queue.dequeue();
                if(message != null)
                    System.out.println("Listener 2: " + message.getContent());
            }
        };
        queue2.addListener(listener2);

        // 发送一条消息到消息中心，所有注册的消息队列都会收到该消息
        Message message = MessageFactory.createMessage("Hello, world!");
        center.broadcast(message);

        // 注销消息队列和监听器
        center.unregisterQueue(queue1);
        center.unregisterQueue(queue2);
        queue1.removeListener(listener1);
        queue2.removeListener(listener2);
    }
}