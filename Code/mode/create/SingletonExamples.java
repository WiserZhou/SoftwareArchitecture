package Code.mode.create;

// 1. 懒汉式（线程不安全）
class SingletonLazyUnsafe {
    private static SingletonLazyUnsafe instance;

    private SingletonLazyUnsafe() {
        // 私有化构造函数
    }

    public static SingletonLazyUnsafe getInstance() {
        if (instance == null) {
            instance = new SingletonLazyUnsafe();
        }
        return instance;
    }
}

// 2. 懒汉式（线程安全，使用synchronized）
class SingletonLazySafe {
    private static SingletonLazySafe instance;

    private SingletonLazySafe() {
        // 私有化构造函数
    }

    public static synchronized SingletonLazySafe getInstance() {
        if (instance == null) {
            instance = new SingletonLazySafe();
        }
        return instance;
    }
}

// 3. 饿汉式
class SingletonEager {
    private static final SingletonEager INSTANCE = new SingletonEager();

    private SingletonEager() {
        // 私有化构造函数
    }

    public static SingletonEager getInstance() {
        return INSTANCE;
    }
}

// 4. 双重检查锁（DCL，Double-Checked Locking）
class SingletonDCL {
    // 使用volatile关键字确保instance变量在多个线程间的可见性
    private static volatile SingletonDCL instance;

    // 私有化构造函数，防止外部实例化
    private SingletonDCL() {
    }

    // 提供全局访问点
    public static SingletonDCL getInstance() {
        // 第一次检查
        if (instance == null) {
            // 同步块，确保线程安全
            synchronized (SingletonDCL.class) {
                // 第二次检查
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }
}

// 5. 静态内部类（推荐方式）
class SingletonHolder {
    private SingletonHolder() {
        // 私有化构造函数
    }

    private static class Holder {
        private static final SingletonHolder INSTANCE = new SingletonHolder();
    }

    public static SingletonHolder getInstance() {
        return Holder.INSTANCE;
    }
}

// 测试类
public class SingletonExamples {
    public static void main(String[] args) {
        // 测试懒汉式（线程不安全）
        SingletonLazyUnsafe instance1 = SingletonLazyUnsafe.getInstance();
        System.out.println("SingletonLazyUnsafe: " + instance1);

        // 测试懒汉式（线程安全）
        SingletonLazySafe instance2 = SingletonLazySafe.getInstance();
        System.out.println("SingletonLazySafe: " + instance2);

        // 测试饿汉式
        SingletonEager instance3 = SingletonEager.getInstance();
        System.out.println("SingletonEager: " + instance3);

        // 测试双重检查锁
        SingletonDCL instance4 = SingletonDCL.getInstance();
        System.out.println("SingletonDCL: " + instance4);

        // 测试静态内部类
        SingletonHolder instance5 = SingletonHolder.getInstance();
        System.out.println("SingletonHolder: " + instance5);
    }
}
