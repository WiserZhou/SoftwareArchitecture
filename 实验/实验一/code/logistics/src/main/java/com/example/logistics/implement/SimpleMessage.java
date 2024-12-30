package com.example.logistics.implement;


import com.example.logistics.interfaces.Message;

/**
 * @description: 简单的消息类，实现了 Message 接口
 * @author: xyc
 * @date: 2023-04-25 16:10
 */
// 简单的消息类，实现了 Message 接口
public class SimpleMessage implements Message {
    private final String content;

    public SimpleMessage(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}