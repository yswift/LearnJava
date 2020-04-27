package generic;

public class Dog extends Animal {
    @Override
    public void sleep() {
        System.out.println("Dog sleep");
    }

    @Override
    public void roam() {
        System.out.println("Dog roam");
    }

    @Override
    public void eat() {
        System.out.println("Dog eat");
    }

    @Override
    public void makeNoise() {
        System.out.println("Dog makeNoise");
    }
}
