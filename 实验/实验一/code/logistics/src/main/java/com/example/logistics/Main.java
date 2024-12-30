package com.example.logistics;

import com.example.logistics.factory.MessageFactory;
import com.example.logistics.gui.MainFrame;
import com.example.logistics.interfaces.MessageCenter;
import com.example.logistics.interfaces.MessageQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // 创建消息中心
        MessageCenter center = MessageFactory.createMessageCenter();
        // 创建一个消息队列并注册到消息中心
        MessageQueue queue = MessageFactory.createMessageQueue(10);
        center.registerQueue(queue);

        // 创建并显示主窗口
        MainFrame mainFrame = new MainFrame(queue);
        mainFrame.setVisible(true);

        // 创建生产者和消费者
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 创建消费者并注册监听器
        Consumer consumer1 = new Consumer(queue, "Consumer 1", mainFrame);
        queue.addListener(consumer1);

        // 提交生产者和消费者任务到线程池
        executor.submit(new Producer(queue, "Producer 1", mainFrame));
        executor.submit(new Producer(queue, "Producer 2", mainFrame));
        executor.submit(new Producer(queue, "Producer 3", mainFrame));
        executor.submit(consumer1);

        // 等待一段时间后，注销消息队列
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        center.unregisterQueue(queue);
        // 关闭线程池
        executor.shutdown();
    }
}