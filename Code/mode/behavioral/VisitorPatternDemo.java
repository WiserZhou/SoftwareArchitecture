package Code.mode.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 抽象元素类
abstract class Staff {
    protected String name;
    protected int kpi; // 员工KPI

    public Staff(String name) {
        this.name = name;
        this.kpi = new Random().nextInt(10); // 随机生成KPI
    }

    // 接受访问者访问
    public abstract void accept(Visitor visitor);
}

// 具体元素类：工程师
class Engineer extends Staff {
    public Engineer(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // 工程师这一年的代码行数
    public int getCodeLines() {
        return new Random().nextInt(10000) + 10000;
    }
}

// 具体元素类：经理
class Manager extends Staff {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // 经理这一年的新产品数量
    public int getProducts() {
        return new Random().nextInt(10);
    }
}

// 抽象访问者类
interface Visitor {
    void visit(Engineer engineer);

    void visit(Manager manager);
}

// 具体访问者类：CEO
class CEOVisitor implements Visitor {
    @Override
    public void visit(Engineer engineer) {
        System.out.println("工程师: " + engineer.name + ", KPI: " + engineer.kpi);
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("经理: " + manager.name + ", KPI: " + manager.kpi + ", 新产品数量: " + manager.getProducts());
    }
}

// 具体访问者类：CTO
class CTOVisitor implements Visitor {
    @Override
    public void visit(Engineer engineer) {
        System.out.println("工程师: " + engineer.name + ", 代码行数: " + engineer.getCodeLines());
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("经理: " + manager.name + ", 产品数量: " + manager.getProducts());
    }
}

// 对象结构类
class BusinessReport {
    private List<Staff> staffList = new ArrayList<>();

    public BusinessReport() {
        staffList.add(new Manager("经理A"));
        staffList.add(new Engineer("工程师A"));
        staffList.add(new Engineer("工程师B"));
        staffList.add(new Manager("经理B"));
        staffList.add(new Engineer("工程师C"));
    }

    // 显示报表
    public void showReport(Visitor visitor) {
        for (Staff staff : staffList) {
            staff.accept(visitor);
        }
    }
}

// 测试类
public class VisitorPatternDemo {
    public static void main(String[] args) {
        BusinessReport report = new BusinessReport();

        System.out.println("===== CEO访问报告 =====");
        report.showReport(new CEOVisitor());

        System.out.println("\n===== CTO访问报告 =====");
        report.showReport(new CTOVisitor());
    }
}
