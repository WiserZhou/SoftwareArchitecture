package Code.mode.behavioral;

import java.util.ArrayList;
import java.util.List;

// 简单中介者类
class SimpleMediator {
    private static SimpleMediator instance = new SimpleMediator(); // 单例模式
    private List<SimpleColleague> colleagues = new ArrayList<>();

    private SimpleMediator() {
    } // 私有化构造函数

    public static SimpleMediator getInstance() {
        return instance;
    }

    public void register(SimpleColleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
        }
    }

    public void relay(SimpleColleague scl) {
        for (SimpleColleague obj : colleagues) {
            if (!obj.equals(scl)) { // 转发给非自身的同事对象
                obj.receive();
            }
        }
    }
}

// 抽象同事类
interface SimpleColleague {
    void receive();

    void send();
}

// 具体同事类1
class SimpleConcreteColleague1 implements SimpleColleague {
    public SimpleConcreteColleague1() {
        SimpleMediator smd = SimpleMediator.getInstance();
        smd.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类1收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类1发出请求...");
        SimpleMediator smd = SimpleMediator.getInstance();
        smd.relay(this); // 请中介者转发
    }
}

// 具体同事类2
class SimpleConcreteColleague2 implements SimpleColleague {
    public SimpleConcreteColleague2() {
        SimpleMediator smd = SimpleMediator.getInstance();
        smd.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类2发出请求...");
        SimpleMediator smd = SimpleMediator.getInstance();
        smd.relay(this); // 请中介者转发
    }
}

// 测试类
public class SimpleMediatorPattern {
    public static void main(String[] args) {
        SimpleColleague c1 = new SimpleConcreteColleague1();
        SimpleColleague c2 = new SimpleConcreteColleague2();

        c1.send();
        System.out.println("----------------");
        c2.send();
    }
}
