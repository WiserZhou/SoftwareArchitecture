package org.example.implement;

import org.example.interfaces.Message;
import org.example.interfaces.MessageListener;
import org.example.interfaces.MessageQueue;

import java.util.ArrayList;
import java.util.List;

public class SimpleMessageQueue implements MessageQueue {
    private int capacity;
    private List<Message> messages = new ArrayList<>();
    private List<MessageListener> listeners = new ArrayList<>();
    private final Object lock = new Object();

    public SimpleMessageQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void enqueue(Message message) throws InterruptedException {
        synchronized (lock) {
            // 如果队列已满，等待（修改部分）
            while (messages.size() == capacity) {
                lock.wait();
            }
            messages.add(message);
            notifyListeners();
            lock.notifyAll();
        }
    }

    @Override
    public Message dequeue() throws InterruptedException {
        synchronized (lock) {
            while (messages.isEmpty()) {
                lock.wait();
            }
            Message message = messages.remove(0);
            notifyListeners();
            lock.notifyAll();
            return message;
        }
    }

    @Override
    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(MessageListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (MessageListener listener : listeners) {
            if (listener != null) {
                listener.onMessageChanged(this);
            }
        }
    }

    public boolean isFull() {
        synchronized (lock) {
            return messages.size() == capacity;
        }
    }
}