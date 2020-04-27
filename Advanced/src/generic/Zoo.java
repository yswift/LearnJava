package generic;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    ArrayList<Animal> list = new ArrayList<>();

    // 向动物园中添加动物
    public void addAnimals(List<? extends Animal> as) {
        for (Animal a : as) {
            list.add(a);
        }
    }

    // 动物数量
    public int animalCount() {
        return list.size();
    }
}
