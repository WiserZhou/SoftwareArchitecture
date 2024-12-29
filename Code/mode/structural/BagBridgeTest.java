package Code.mode.structural;

// 实现化角色：颜色
interface Color {
    String getColor();
}

// 具体实现化角色：黄色
class Yellow implements Color {
    @Override
    public String getColor() {
        return "yellow";
    }
}

// 具体实现化角色：红色
class Red implements Color {
    @Override
    public String getColor() {
        return "red";
    }
}

// 抽象化角色：包
abstract class Bag {
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract String getName();
}

// 扩展抽象化角色：手提包
class HandBag extends Bag {
    @Override
    public String getName() {
        return color.getColor() + " HandBag";
    }
}

// 扩展抽象化角色：钱包
class Wallet extends Bag {
    @Override
    public String getName() {
        return color.getColor() + " Wallet";
    }
}

// 测试类
public class BagBridgeTest {
    public static void main(String[] args) {
        Bag handBag = new HandBag();
        handBag.setColor(new Yellow());
        System.out.println(handBag.getName());

        Bag wallet = new Wallet();
        wallet.setColor(new Red());
        System.out.println(wallet.getName());
    }
}
