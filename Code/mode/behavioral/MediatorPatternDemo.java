package Code.mode.behavioral;

import java.util.ArrayList;
import java.util.List;

// 抽象中介者
abstract class Mediator {
    public abstract void register(Colleague colleague); // 注册同事对象

    public abstract void relay(Colleague cl); // 转发
}

// 具体中介者
/**
 * ConcreteMediator 是具体的中介者类，继承自抽象中介者 Mediator。
 * 它负责在不同的 Colleague 对象之间进行通信和协调。
 */
class ConcreteMediator extends Mediator {
    private List<Colleague> colleagues = new ArrayList<>();

    /**
     * 注册一个同事对象到中介者中。
     * 如果该同事对象尚未注册，则将其添加到同事列表中，并设置其中介者为当前中介者。
     *
     * @param colleague 要注册的同事对象
     */
    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMedium(this);
        }
    }

    /**
     * 转发消息给所有已注册的同事对象，除了发送消息的同事对象自身。
     *
     * @param cl 发送消息的同事对象
     */
    @Override
    public void relay(Colleague cl) {
        for (Colleague obj : colleagues) {
            if (!obj.equals(cl)) { // 转发给非自身的同事对象
                obj.receive();
            }
        }
    }
}

// 抽象同事类
abstract class Colleague {
    protected Mediator mediator;

    public void setMedium(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();

    public abstract void send();
}

// 具体同事类1
class ConcreteColleague1 extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类1收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类1发出请求...");
        mediator.relay(this); // 请中介者转发
    }
}

// 具体同事类2
class ConcreteColleague2 extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类2发出请求...");
        mediator.relay(this); // 请中介者转发
    }
}

// 测试类
public class MediatorPatternDemo {
    public static void main(String[] args) {
        Mediator md = new ConcreteMediator();
        Colleague c1 = new ConcreteColleague1();
        Colleague c2 = new ConcreteColleague2();

        md.register(c1);
        md.register(c2);

        c1.send();
        System.out.println("----------------");
        c2.send();
    }
}
