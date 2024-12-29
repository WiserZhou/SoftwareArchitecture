package Code.principle;

// 计算机类
class Computer {
    // 保存当前任务
    public void saveCurrentTask() {
        System.out.println("保存当前任务...");
    }

    // 关闭服务
    public void closeService() {
        System.out.println("关闭所有服务...");
    }

    // 关闭屏幕
    public void closeScreen() {
        System.out.println("关闭屏幕...");
    }

    // 关闭电源
    public void closePower() {
        System.out.println("关闭电源...");
    }

    // 总的关闭方法，封装细节
    public void close() {
        saveCurrentTask();
        closeService();
        closeScreen();
        closePower();
    }
}

// 人类
class Person {
    private Computer computer;

    // 构造方法，依赖注入
    public Person(Computer computer) {
        this.computer = computer;
    }

    // 按下关闭按钮
    public void clickCloseButton() {
        System.out.println("按下关闭按钮，执行关闭操作...");
        computer.close();
    }
}

// 主类，测试代码
public class DemeterPrinciple {
    public static void main(String[] args) {
        // 创建一个计算机对象
        Computer computer = new Computer();

        // 创建一个人，并将计算机传递给他
        Person person = new Person(computer);

        // 人按下关闭按钮
        person.clickCloseButton();
    }
}
