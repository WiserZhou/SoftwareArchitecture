package Code.mode.structural;

// 实现化角色接口
interface Implementor {
    void operationImpl();
}

// 具体实现化角色
class ConcreteImplementor implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("具体实现化角色(ConcreteImplementor)的方法被调用");
    }
}

// 抽象化角色
abstract class Abstraction {
    protected Implementor implementor;

    protected Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}

// 扩展抽象化角色
class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("扩展抽象化角色(RefinedAbstraction)的方法被调用");
        implementor.operationImpl();
    }
}

// 适配者类
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者(Adaptee)的方法被调用");
    }
}

// 适配器类，将适配者连接到实现化角色接口
class ObjectAdapter implements Implementor {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void operationImpl() {
        // 调用适配者的方法
        adaptee.specificRequest();
    }
}

// 测试类
public class BridgeAdapterDemo {
    public static void main(String[] args) {
        // 使用具体实现化角色
        Implementor concreteImpl = new ConcreteImplementor();
        Abstraction abstraction = new RefinedAbstraction(concreteImpl);
        abstraction.operation();

        System.out.println("----------------------------");

        // 使用适配器，将适配者连接到抽象化角色
        Adaptee adaptee = new Adaptee();
        Implementor adapter = new ObjectAdapter(adaptee);
        Abstraction abstractionWithAdapter = new RefinedAbstraction(adapter);
        abstractionWithAdapter.operation();
    }
}
