package org.example.implement;

import org.example.exception.MessageQueueException;
import org.example.interfaces.Message;
import org.example.interfaces.MessageQueue;

public class RegisterListener {
    public void register(MessageQueue queue, int number) throws MessageQueueException {
        Message message = queue.dequeue();
        System.out.println("listener"+number+":"+message.getContent());
    }
}
