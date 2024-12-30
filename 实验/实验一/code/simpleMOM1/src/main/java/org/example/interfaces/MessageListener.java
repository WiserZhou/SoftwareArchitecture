package org.example.interfaces;

import org.example.exception.MessageQueueException;

public interface MessageListener {
    void onMessageChanged(MessageQueue queue) throws MessageQueueException;
}