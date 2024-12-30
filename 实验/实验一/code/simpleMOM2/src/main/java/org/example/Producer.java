package org.example;


import org.example.factory.MessageFactory;
import org.example.interfaces.Message;
import org.example.interfaces.MessageQueue;

// 生产者类，用于向消息队列中添加消息
public class Producer implements Runnable {
    private final MessageQueue queue;
    private final String name;

    public Producer(MessageQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Message message = MessageFactory.createMessage("Message " + i + " from " + name);
            try {
                queue.enqueue(message);
                System.out.println("Producer " + name + " put " + message.getContent());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}