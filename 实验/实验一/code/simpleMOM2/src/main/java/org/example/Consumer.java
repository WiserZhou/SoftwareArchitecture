package org.example;


import org.example.interfaces.Message;
import org.example.interfaces.MessageQueue;

// 消费者类，用于从消息队列中取出消息并处理
public class Consumer implements Runnable {
    private final MessageQueue queue;
    private final String name;

    public Consumer(MessageQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = queue.dequeue();
                System.out.println("Consumer " + name + " got " + message.getContent());
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}