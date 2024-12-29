package Code.principle;

// 主类，演示依赖倒置原则
public class DependencyInversion {
    public static void main(String[] args) {
        Person person = new Person();

        // 接收电子邮件
        person.receive(new Email());

        // 接收微信消息
        person.receive(new WeChat());
    }
}

// 接收接口（抽象层）
interface IReceive {
    String getInfo();
}

// Email 实现 IReceive 接口
class Email implements IReceive {
    @Override
    public String getInfo() {
        return "电子邮件信息: hello, Email!";
    }
}

// WeChat 实现 IReceive 接口
class WeChat implements IReceive {
    @Override
    public String getInfo() {
        return "微信信息: hello, WeChat!";
    }
}

// Person 类，依赖抽象接口 IReceive
class Person {
    public void receive(IReceive receive) {
        System.out.println(receive.getInfo());
    }
}
