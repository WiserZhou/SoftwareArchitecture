package Code.principle;

// 吃的动作接口
interface EatAnimalAction {
    void eat();
}

// 飞的动作接口
interface FlyAnimalAction {
    void fly();
}

// 游泳的动作接口
interface SwimAnimalAction {
    void swim();
}

// 狗类实现吃和游泳的接口
class Dog implements EatAnimalAction, SwimAnimalAction {
    @Override
    public void eat() {
        System.out.println("狗在吃东西...");
    }

    @Override
    public void swim() {
        System.out.println("狗在游泳...");
    }
}

// 鸟类实现吃和飞的接口
class Bird implements EatAnimalAction, FlyAnimalAction {
    @Override
    public void eat() {
        System.out.println("鸟在吃东西...");
    }

    @Override
    public void fly() {
        System.out.println("鸟在飞翔...");
    }
}

// 鱼类实现吃和游泳的接口
class Fish implements EatAnimalAction, SwimAnimalAction {
    @Override
    public void eat() {
        System.out.println("鱼在吃东西...");
    }

    @Override
    public void swim() {
        System.out.println("鱼在游泳...");
    }
}

// 测试类
public class InterfaceSegregationTest {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.swim();

        Bird bird = new Bird();
        bird.eat();
        bird.fly();

        Fish fish = new Fish();
        fish.eat();
        fish.swim();
    }
}
