package org.example.exception;

public class MessageQueueException extends Exception {
    public MessageQueueException(String message) {
        super(message);
    }

    public MessageQueueException(String message, Throwable cause) {
        super(message, cause);
    }
} 