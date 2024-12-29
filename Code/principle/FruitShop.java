package Code.principle;

// 水果的基类
abstract class Fruit {
    // 出售的方法
    public abstract void sell();
}

// 苹果
class Apple extends Fruit {

    @Override
    public void sell() {
        System.out.println("卖出一斤苹果！");
    }
}

// 香蕉
class Banana extends Fruit {

    @Override
    public void sell() {
        System.out.println("卖出一斤香蕉！");
    }
}

// 西瓜
class Watermelon extends Fruit {

    @Override
    public void sell() {
        System.out.println("卖出一斤西瓜！");
    }
}

// 水果店主类
public class FruitShop {
    // 卖水果的方法
    public void sellFruit(Fruit fruit) {
        fruit.sell();
    }

    // 测试代码
    public static void main(String[] args) {
        FruitShop shop = new FruitShop();

        Fruit apple = new Apple();
        Fruit banana = new Banana();
        Fruit watermelon = new Watermelon();

        shop.sellFruit(apple);
        shop.sellFruit(banana);
        shop.sellFruit(watermelon);
    }
}
