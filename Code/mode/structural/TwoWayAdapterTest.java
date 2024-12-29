package Code.mode.structural;

// 目标接口
interface TwoWayTarget {
    void request();
}

// 适配者接口
interface TwoWayAdaptee {
    void specificRequest();
}

// 目标实现类
class TargetRealize implements TwoWayTarget {
    @Override
    public void request() {
        System.out.println("目标代码被调用！");
    }
}

// 适配者实现类
class AdapteeRealize implements TwoWayAdaptee {
    @Override
    public void specificRequest() {
        System.out.println("适配者代码被调用！");
    }
}

// 双向适配器类，实现目标接口和适配者接口
class TwoWayAdapter implements TwoWayTarget, TwoWayAdaptee {
    private TwoWayTarget target;
    private TwoWayAdaptee adaptee;

    // 通过目标对象构造
    public TwoWayAdapter(TwoWayAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    // 通过适配者对象构造
    public TwoWayAdapter(TwoWayTarget target) {
        this.target = target;
    }

    @Override
    public void request() {
        if (adaptee != null) {
            adaptee.specificRequest();
        }
    }

    @Override
    public void specificRequest() {
        if (target != null) {
            target.request();
        }
    }
}

// 测试双向适配器
public class TwoWayAdapterTest {
    public static void main(String[] args) {
        System.out.println("目标通过双向适配器访问适配者：");
        TwoWayAdaptee adaptee = new AdapteeRealize();
        TwoWayTarget target = new TwoWayAdapter(adaptee);
        target.request();

        System.out.println("-----------------------------");

        System.out.println("适配者通过双向适配器访问目标：");
        TwoWayTarget targetRealize = new TargetRealize();
        TwoWayAdaptee adapteeViaAdapter = new TwoWayAdapter(targetRealize);
        adapteeViaAdapter.specificRequest();
    }
}
