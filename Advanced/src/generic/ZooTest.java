package generic;

import java.util.Arrays;
import java.util.List;

public class ZooTest {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        System.out.println("before add animal, count = " + zoo.animalCount());
        // 创建dog list 放入两只狗
        List<Dog> dogs = Arrays.asList(new Dog(), new Dog());
        // 加到动物园
        zoo.addAnimals(dogs);
        System.out.println("after add animal, count = " + zoo.animalCount());
    }
}
