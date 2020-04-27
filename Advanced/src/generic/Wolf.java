package generic;

public class Wolf extends Animal {
    @Override
    public void sleep() {
        System.out.println("wolf sleep");
    }

    @Override
    public void roam() {
        System.out.println("wolf roam");
    }

    @Override
    public void eat() {
        System.out.println("wolf eat");
    }

    @Override
    public void makeNoise() {
        System.out.println("wolf makeNoise");
    }
}
