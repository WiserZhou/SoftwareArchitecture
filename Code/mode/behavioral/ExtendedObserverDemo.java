package Code.mode.behavioral;

import java.util.Observable;
import java.util.Observer;

// 具体目标类：原油期货
class OilFutures extends Observable {
    private float price; // 原油价格

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        super.setChanged(); // 设置内部标志位，表明数据发生了变化
        super.notifyObservers(price); // 通知所有观察者
        this.price = price;
    }
}

// 具体观察者类：多方
class Bull implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        float price = ((Float) arg).floatValue();
        if (price > 0) {
            System.out.println("多方认为：油价上涨了" + price + "元，继续加仓！");
        } else {
            System.out.println("多方认为：油价下跌了" + (-price) + "元，赶紧减仓！");
        }
    }
}

// 具体观察者类：空方
class Bear implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        float price = ((Float) arg).floatValue();
        if (price > 0) {
            System.out.println("空方认为：油价上涨了" + price + "元，开始亏损！");
        } else {
            System.out.println("空方认为：油价下跌了" + (-price) + "元，继续获利！");
        }
    }
}

// 测试类
public class ExtendedObserverDemo {
    public static void main(String[] args) {
        OilFutures oil = new OilFutures();
        Observer bull = new Bull(); // 多方
        Observer bear = new Bear(); // 空方

        // 添加观察者
        oil.addObserver(bull);
        oil.addObserver(bear);

        // 改变价格，通知观察者
        oil.setPrice(5.0f);
        System.out.println("--------------------");
        oil.setPrice(-3.0f);
    }
}
