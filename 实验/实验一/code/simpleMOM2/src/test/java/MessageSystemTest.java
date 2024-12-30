import org.example.factory.MessageFactory;
import org.example.interfaces.Message;
import org.example.interfaces.MessageCenter;
import org.example.interfaces.MessageQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageSystemTest {

    @Test
    void testMessageCreation() {
        // 测试策略：测试消息对象的创建和内容获取
        Message message = MessageFactory.createMessage("Test Message");
        assertEquals("Test Message", message.getContent());
    }

    @Test
    void testQueueEnqueueDequeue() throws InterruptedException {
        // 测试策略：测试消息队列的入队和出队功能
        MessageQueue queue = MessageFactory.createMessageQueue(10);
        Message message = MessageFactory.createMessage("Test Message");
        queue.enqueue(message);
        Message dequeuedMessage = queue.dequeue();
        assertEquals("Test Message", dequeuedMessage.getContent());
    }

    @Test
    void testQueueCapacity() throws InterruptedException {
        // 测试策略：测试消息队列的容量限制
        MessageQueue queue = MessageFactory.createMessageQueue(10);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(MessageFactory.createMessage("Message " + i));
        }

        // 尝试向已满的队列中添加消息，并捕获预期的 InterruptedException
        try {
            queue.enqueue(MessageFactory.createMessage("Overflow Message"));
            fail("Expected InterruptedException to be thrown");
        } catch (InterruptedException e) {
            // Expected exception
        }
    }

    @Test
    void testRegisterAndUnregisterQueue() {
        // 测试策略：测试消息中心的注册和注销功能
        MessageCenter center = MessageFactory.createMessageCenter();
        MessageQueue queue = MessageFactory.createMessageQueue(10);
        center.registerQueue(queue);
        assertDoesNotThrow(() -> center.broadcast(MessageFactory.createMessage("Broadcast Message"), queue));
        center.unregisterQueue(queue);

        // 测试注销后的行为
        assertThrows(InterruptedException.class, () -> {
            queue.enqueue(MessageFactory.createMessage("Message after unregister"));
        });
    }

    @Test
    void testBroadcastMessage() throws InterruptedException {
        // 测试策略：测试消息中心的广播功能
        MessageCenter center = MessageFactory.createMessageCenter();
        MessageQueue queue = MessageFactory.createMessageQueue(10);
        center.registerQueue(queue);
        Message message = MessageFactory.createMessage("Broadcast Message");
        center.broadcast(message, queue);
        Message dequeuedMessage = queue.dequeue();
        assertEquals("Broadcast Message", dequeuedMessage.getContent());
    }
}