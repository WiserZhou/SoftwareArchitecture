package org.example.factory;


import org.example.implement.SimpleMessage;
import org.example.implement.SimpleMessageCenter;
import org.example.implement.SimpleMessageQueue;
import org.example.interfaces.Message;
import org.example.interfaces.MessageCenter;
import org.example.interfaces.MessageQueue;

/**
 * @description: 负责创建消息、消息队列和消息中心的实例。
 * @author: xyc
 * @date: 2023-04-25 16:07
 */
public class MessageFactory {
    // 私有化构造函数，禁止外部创建对象
    private MessageFactory() {}

    // 创建一个Message对象
    public static Message createMessage(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Message content cannot be null");
        }
        return new SimpleMessage(content);
    }

    // 创建一个MessageQueue对象
    public static MessageQueue createMessageQueue() {
        return new SimpleMessageQueue();
    }

    // 创建一个MessageCenter对象
    public static MessageCenter createMessageCenter() {
        return SimpleMessageCenter.INSTANCE;
    }
}