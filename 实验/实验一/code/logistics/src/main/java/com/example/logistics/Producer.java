package com.example.logistics;

import com.example.logistics.factory.MessageFactory;
import com.example.logistics.gui.MainFrame;
import com.example.logistics.interfaces.MessageQueue;

import java.util.Random;

public class Producer implements Runnable {
    private final MessageQueue queue;
    private final String name;
    private final MainFrame mainFrame;

    public Producer(MessageQueue queue, String name, MainFrame mainFrame) {
        this.queue = queue;
        this.name = name;
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        System.out.println("thread id" + Thread.currentThread().getId());
        int temp = new Random().nextInt(100);
        for (int i = temp; i < temp + 3; i++) {
            LogisticsInfo message = MessageFactory.createMessage(Integer.toString(i), "未出库");
            try {
                queue.enqueue(message);
                String log = "生产者 " + name + " 将包裹信息放入消息队列中：" + "Package " + message.getPackageId() + " Status " + message.getStatus();
                System.out.println(log);
                mainFrame.getLogPanel().appendLog(log);
                mainFrame.getTaskPanel().addTask(message);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}