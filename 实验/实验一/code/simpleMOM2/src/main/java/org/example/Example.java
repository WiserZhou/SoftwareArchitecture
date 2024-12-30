package org.example;


import org.example.factory.MessageFactory;
import org.example.interfaces.MessageCenter;
import org.example.interfaces.MessageQueue;

public class Example {
    public static void main(String[] args) {
        // 创建消息中心
        MessageCenter center = MessageFactory.createMessageCenter();

        // 创建一个消息队列并注册到消息中心
        MessageQueue queue = MessageFactory.createMessageQueue(10);
        center.registerQueue(queue);

        // 创建两个生产者和两个消费者
        Producer producer1 = new Producer(queue, "Producer 1");
        Producer producer2 = new Producer(queue, "Producer 2");
        Consumer consumer1 = new Consumer(queue, "Consumer 1");
        Consumer consumer2 = new Consumer(queue, "Consumer 2");
        //启动两个生产者线程，用于向消息队列发送消息
        new Thread(producer1).start();
        new Thread(producer2).start();
        //启动两个消费者线程，用于从消息队列接收消息
        new Thread(consumer1).start();
        new Thread(consumer2).start();

        // 稍微等待一段时间后，注销消息队列
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        center.unregisterQueue(queue);
        System.out.println("======");
    }
}