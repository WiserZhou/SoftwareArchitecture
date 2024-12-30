package Code.mode.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 接口定义
interface Car {
    void run();

    void stop();
}

// 具体实现类
class Benz implements Car {
    @Override
    public void run() {
        System.out.println("Benz is running...");
    }

    @Override
    public void stop() {
        System.out.println("Benz has stopped.");
    }
}

// 横切逻辑：方法前的增强
class CarUtil {
    public static void methodBefore() {
        System.out.println("Method Before: Performing pre-checks before method execution.");
    }

    public static void methodAfter() {
        System.out.println("Method After: Cleaning up after method execution.");
    }
}

// 动态代理的核心逻辑
class MyInvocationHandler implements InvocationHandler {
    private Object target;

    // 设置代理目标对象
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法前增强
        CarUtil.methodBefore();
        // 调用目标对象的方法
        Object result = method.invoke(target, args);
        // 方法后增强
        CarUtil.methodAfter();
        return result;
    }
}

// 代理工厂类
class ProxyFactory {
    public static Object getProxy(Object target) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setTarget(target);
        // 创建代理对象
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
    }
}

// 测试类
public class JdkDynamicProxyDemo {
    public static void main(String[] args) {
        // 创建目标对象
        Car car = new Benz();
        // 获取代理对象
        Car proxyCar = (Car) ProxyFactory.getProxy(car);
        // 调用代理对象的方法
        proxyCar.run();
        System.out.println("----------------------------");
        proxyCar.stop();
    }
}
