package Code.mode.structural;

// 目标接口
interface Target {
    void request();
}

// 适配者类
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}

// 类适配器：通过继承适配者并实现目标接口
class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        // 调用适配者的方法
        specificRequest();
    }
}

// 测试类适配器
public class ClassAdapterTest {
    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }
}
