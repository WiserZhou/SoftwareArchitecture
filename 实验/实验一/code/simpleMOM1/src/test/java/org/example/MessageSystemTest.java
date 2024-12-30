package org.example;

import org.example.exception.MessageQueueException;
import org.example.factory.MessageFactory;
import org.example.interfaces.Message;
import org.example.interfaces.MessageCenter;
import org.example.interfaces.MessageListener;
import org.example.interfaces.MessageQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 消息系统集成测试类
 * 测试策略：
 * 1. 基础功能测试：消息的广播和接收
 * 2. 队列管理测试：队列的注册和注销
 * 3. 监听器管理测试：监听器的添加和移除
 * 4. 边界条件测试：空队列处理
 * 5. 性能测试：多消息处理
 */
public class MessageSystemTest {
    private MessageCenter messageCenter;
    private MessageQueue queue1;
    private MessageQueue queue2;
    private StringBuilder testResult;

    /**
     * 测试前初始化
     * - 创建消息中心实例
     * - 创建两个消息队列用于测试
     * - 初始化结果收集器
     */
    @Before
    public void setup() {
        messageCenter = MessageFactory.createMessageCenter();
        queue1 = MessageFactory.createMessageQueue();
        queue2 = MessageFactory.createMessageQueue();
        testResult = new StringBuilder();
    }

    /**
     * 测试基本的消息广播功能
     * 验证点：
     * 1. 多个队列能否正确接收到广播的消息
     * 2. 消息内容是否正确传递
     * 3. 监听器是否按预期处理消息
     */
    @Test
    public void testBasicMessageBroadcast() throws InterruptedException, MessageQueueException {
        // 注册队列
        messageCenter.registerQueue(queue1);
        messageCenter.registerQueue(queue2);

        // 创建监听器
        MessageListener listener1 = queue -> {
            Message msg = queue.dequeue();
            if (msg != null) {
                testResult.append("Listener1: ").append(msg.getContent()).append(";");
            }
        };
        
        MessageListener listener2 = queue -> {
            Message msg = queue.dequeue();
            if (msg != null) {
                testResult.append("Listener2: ").append(msg.getContent()).append(";");
            }
        };

        queue1.addListener(listener1);
        queue2.addListener(listener2);

        // 广播消息
        Message message = MessageFactory.createMessage("Test Message");
        messageCenter.broadcast(message);

        // 验证结果
        String expected = "Listener1: Test Message;Listener2: Test Message;";
        assertEquals(expected, testResult.toString());
    }

    /**
     * 测试队列注销功能
     * 验证点：
     * 1. 注销后的队列是否还会收到消息
     * 2. 其他队列是否正常工作
     * 3. 系统的消息分发是否准确
     */
    @Test
    public void testQueueUnregistration() throws InterruptedException, MessageQueueException {
        messageCenter.registerQueue(queue1);
        messageCenter.registerQueue(queue2);

        StringBuilder result = new StringBuilder();
        MessageListener listener = queue -> {
            Message msg = queue.dequeue();
            if (msg != null) {
                result.append(msg.getContent());
            }
        };

        queue1.addListener(listener);
        queue2.addListener(listener);

        // 注销queue2
        messageCenter.unregisterQueue(queue2);

        Message message = MessageFactory.createMessage("Test Unregister");
        messageCenter.broadcast(message);

        // 只有queue1应该收到消息
        assertEquals("Test Unregister", result.toString());
    }

    /**
     * 测试监听器移除功能
     * 验证点：
     * 1. 移除的监听器是否还会收到消息
     * 2. 其他监听器是否正常工作
     * 3. 消息处理的准确性
     */
    @Test
    public void testListenerRemoval() throws InterruptedException, MessageQueueException {
        messageCenter.registerQueue(queue1);

        StringBuilder result = new StringBuilder();
        MessageListener listener1 = queue -> {
            Message msg = queue.dequeue();
            if (msg != null) {
                result.append("L1:").append(msg.getContent());
            }
        };

        MessageListener listener2 = queue -> {
            Message msg = queue.dequeue();
            if (msg != null) {
                result.append("L2:").append(msg.getContent());
            }
        };

        queue1.addListener(listener1);
        queue1.addListener(listener2);
        
        // 移除listener2
        queue1.removeListener(listener2);

        Message message = MessageFactory.createMessage("Test Remove");
        messageCenter.broadcast(message);

        // 只有listener1应该收到消息
        assertEquals("L1:Test Remove", result.toString());
    }

    /**
     * 测试空队列的行为
     * 验证点：
     * 1. 空队列的dequeue操作是否返回null
     * 2. 系统对空队列的处理是否合理
     */
    @Test
    public void testEmptyQueue() throws MessageQueueException {
        MessageQueue queue = MessageFactory.createMessageQueue();
        assertNull(queue.dequeue());
    }

    /**
     * 测试多消息处理能力
     * 验证点：
     * 1. 系统能否正确处理连续的消息
     * 2. 消息的处理顺序是否正确
     * 3. 所有消息是否都被正确传递
     */
    @Test
    public void testMultipleMessages() throws InterruptedException, MessageQueueException {
        messageCenter.registerQueue(queue1);

        StringBuilder result = new StringBuilder();
        MessageListener listener = queue -> {
            Message msg = queue.dequeue();
            if (msg != null) {
                result.append(msg.getContent()).append(",");
            }
        };

        queue1.addListener(listener);

        // 发送多条消息
        Message message1 = MessageFactory.createMessage("Message1");
        Message message2 = MessageFactory.createMessage("Message2");
        Message message3 = MessageFactory.createMessage("Message3");

        messageCenter.broadcast(message1);
        messageCenter.broadcast(message2);
        messageCenter.broadcast(message3);

        assertEquals("Message1,Message2,Message3,", result.toString());
    }
} 