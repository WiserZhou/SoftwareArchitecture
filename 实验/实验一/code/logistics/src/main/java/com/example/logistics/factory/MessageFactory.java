package com.example.logistics.factory;



import com.example.logistics.LogisticsInfo;
import com.example.logistics.implement.SimpleMessage;
import com.example.logistics.implement.SimpleMessageCenter;
import com.example.logistics.implement.SimpleMessageQueue;

import com.example.logistics.interfaces.Message;
import com.example.logistics.interfaces.MessageCenter;
import com.example.logistics.interfaces.MessageQueue;

public class MessageFactory {
    // 私有化构造函数，禁止外部创建对象
    private MessageFactory() {}

    // 创建一个Message对象
    public static LogisticsInfo createMessage(String id, String status) {
        return new LogisticsInfo(id, status);
    }

    // 创建一个MessageQueue对象
    public static MessageQueue createMessageQueue(int capacity) {
        return new SimpleMessageQueue(capacity);
    }

    // 创建一个MessageCenter对象
    public static MessageCenter createMessageCenter() {
        return SimpleMessageCenter.getInstance();
    }
}
