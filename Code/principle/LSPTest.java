package Code.principle;

// 主类，入口程序
public class LSPTest {
    public static void main(String[] args) {
        // 测试燕子
        Bird swallow = new Swallow();
        swallow.setFlySpeed(20);
        System.out.println("燕子飞行时间: " + swallow.getFlyTime(100) + " 小时");

        // 测试几维鸟
        Bird kiwi = new BrownKiwi();
        kiwi.setRunSpeed(5);
        System.out.println("几维鸟运行时间: " + kiwi.getRunTime(100) + " 小时");

        // 测试对几维鸟调用飞行方法（应抛出异常）
        try {
            kiwi.setFlySpeed(10);
        } catch (UnsupportedOperationException e) {
            System.out.println("错误: " + e.getMessage());
        }
    }
}

// 动物类 - 基类
abstract class Animal {
    // 动物运行速度
    protected double runSpeed;

    // 设置运行速度
    public void setRunSpeed(double speed) {
        this.runSpeed = speed;
    }

    // 计算运行时间
    public double getRunTime(double distance) {
        if (runSpeed <= 0) {
            throw new IllegalArgumentException("运行速度必须大于0");
        }
        return distance / runSpeed;
    }
}

// 鸟类 - 继承动物类
abstract class Bird extends Animal {
    // 鸟的飞行速度
    protected double flySpeed;

    // 设置飞行速度
    public void setFlySpeed(double speed) {
        this.flySpeed = speed;
    }

    // 计算飞行时间
    public double getFlyTime(double distance) {
        if (flySpeed <= 0) {
            throw new IllegalArgumentException("飞行速度必须大于0");
        }
        return distance / flySpeed;
    }
}

// 燕子类 - 继承鸟类
class Swallow extends Bird {
    // 燕子可以飞行，使用 Bird 的飞行相关方法
}

// 几维鸟类 - 特殊鸟类
class BrownKiwi extends Bird {
    @Override
    public void setFlySpeed(double speed) {
        throw new UnsupportedOperationException("几维鸟不会飞！");
    }

    @Override
    public double getFlyTime(double distance) {
        throw new UnsupportedOperationException("几维鸟不会飞！");
    }
}
