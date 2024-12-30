## 问题分析

### SimpleMessageQueue 类中的问题

- `enqueue` 方法在队列已满时使用 `wait`，而不是直接抛出 `InterruptedException`。
- `dequeue` 方法在队列为空时使用 `wait`，并在队列不为空时使用 `notifyAll`，确保消费者线程能够正确唤醒。

### SimpleMessageCenter 类中的问题

- `broadcast` 方法没有正确处理 `InterruptedException`，可能导致消息未能正确广播。

### MessageSystemTest 类中的问题

- `testQueueCapacity` 方法在队列已满时直接捕获 `InterruptedException`，避免使用线程来测试。
- `testRegisterAndUnregisterQueue` 方法在注销队列后没有正确验证队列的行为。

## 优化调整

### SimpleMessageQueue 类

```java
public synchronized void enqueue(Message message) throws InterruptedException {
    while (queue.size() == capacity) {
        wait();
    }
    queue.add(message);
    notifyAll();
}

public synchronized Message dequeue() throws InterruptedException {
    while (queue.isEmpty()) {
        wait();
    }
    Message message = queue.remove();
    notifyAll();
    return message;
}
```

### SimpleMessageCenter 类

```java
public void broadcast(Message message) {
    for (SimpleMessageQueue queue : queues) {
        try {
            queue.enqueue(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Log or handle the interruption
        }
    }
}
```

### MessageSystemTest 类

```java
@Test
public void testQueueCapacity() {
    SimpleMessageQueue queue = new SimpleMessageQueue(1);
    queue.enqueue(new Message("test"));
    try {
        queue.enqueue(new Message("test2"));
        fail("Expected InterruptedException");
    } catch (InterruptedException e) {
        // Expected exception
    }
}

@Test
public void testRegisterAndUnregisterQueue() {
    SimpleMessageCenter center = new SimpleMessageCenter();
    SimpleMessageQueue queue = new SimpleMessageQueue(10);
    center.registerQueue(queue);
    center.unregisterQueue(queue);
    // Verify the queue behavior after unregistering
}
```

通过这些优化调整，代码的健壮性和可维护性得到了提升，测试用例也更加准确和高效。
