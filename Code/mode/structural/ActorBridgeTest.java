package Code.mode.structural;

// 实现化角色接口
interface Implementor {
    void operationImpl();
}

// 具体实现化角色 A
class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("具体实现化(ConcreteImplementorA)角色被访问");
    }
}

// 具体实现化角色 B
class ConcreteImplementorB implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("具体实现化(ConcreteImplementorB)角色被访问");
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
        System.out.println("扩展抽象化(RefinedAbstraction)角色被访问");
        implementor.operationImpl();
    }
}

// 测试类
public class ActorBridgeTest {
    public static void main(String[] args) {
        Implementor implA = new ConcreteImplementorA();
        Abstraction abstractionA = new RefinedAbstraction(implA);
        abstractionA.operation();

        Implementor implB = new ConcreteImplementorB();
        Abstraction abstractionB = new RefinedAbstraction(implB);
        abstractionB.operation();
    }
}
