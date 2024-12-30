package com.example.logistics;

import com.example.logistics.gui.MainFrame;
import com.example.logistics.interfaces.MessageListener;
import com.example.logistics.interfaces.MessageQueue;

public class Consumer implements Runnable, MessageListener {
    private final MessageQueue queue;
    private final String name;
    private final MainFrame mainFrame;

    public Consumer(MessageQueue queue, String name, MainFrame mainFrame) {
        this.queue = queue;
        this.name = name;
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (mainFrame.isConsumerRunning()) {
                    LogisticsInfo message = queue.dequeue();
                    String log = "消费者" + name + "获取到信息：Package ID: " + message.getPackageId() + " Status: " + message.getStatus();
                    System.out.println(log);
                    mainFrame.getLogPanel().appendLog(log);
                    mainFrame.getTaskPanel().updateTask(message);
                    Thread.sleep(200);
                } else {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMessageChanged(LogisticsInfo logisticsInfo) {
        if (mainFrame.isConsumerRunning()) {
            String log = "消费者" + name + "监听到信息变化：Package ID: " + logisticsInfo.getPackageId() + " Status: " + logisticsInfo.getStatus();
            System.out.println(log);
            mainFrame.getLogPanel().appendLog(log);
            mainFrame.getTaskPanel().updateTask(logisticsInfo);
        }
    }
}