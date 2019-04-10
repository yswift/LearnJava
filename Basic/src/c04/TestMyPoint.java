package c04;

public class TestMyPoint {
    public static void main(String[] args) {
        MyPoint p1 = new MyPoint();
//        p1.setX(1);
//        p1.setY(2);
        MyPoint p2 = new MyPoint();
        double d = p1.distance(p2);
        System.out.printf("%.2f\n", d);
    }
}
