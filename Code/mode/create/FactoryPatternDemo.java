package Code.mode.create;

// Shape接口
interface Shape {
    void draw();
}

// Rectangle类
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

// Square类
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

// Circle类
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

// 工厂类
class ShapeFactory {
    // 根据类型获取Shape对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}

// 主类，测试代码
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // 获取 Circle 对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        // 获取 Rectangle 对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        // 获取 Square 对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
