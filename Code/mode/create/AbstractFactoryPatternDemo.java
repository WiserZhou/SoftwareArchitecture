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

// Circle类
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

// Square类
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

// Color接口
interface Color {
    void fill();
}

// Red类
class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}

// Green类
class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}

// Blue类
class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}

// 抽象工厂
abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}

// 形状工厂
class ShapeFactory extends AbstractFactory {
    @Override
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

    @Override
    public Color getColor(String color) {
        return null; // ShapeFactory不支持获取颜色
    }
}

// 颜色工厂
class ColorFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shapeType) {
        return null; // ColorFactory不支持获取形状
    }

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }
}

// 工厂生成器
class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}

// 测试类
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // 获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        // 获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        // 获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        // 获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();

        // 获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        // 获取 Red 的对象，并调用它的 fill 方法
        Color color1 = colorFactory.getColor("RED");
        color1.fill();

        // 获取 Green 的对象，并调用它的 fill 方法
        Color color2 = colorFactory.getColor("GREEN");
        color2.fill();

        // 获取 Blue 的对象，并调用它的 fill 方法
        Color color3 = colorFactory.getColor("BLUE");
        color3.fill();
    }
}
