package generic;

public class PairTest2 {
    public static void main(String[] args) {
        // 当调用泛型方法时，在方法前的<>中放入具体类型
        String m1 = ArrayAlg.<String>getMiddle("John", "Q", "Public");
        System.out.println("m1 = " + m1);
        // 也可以省略<String>类型参数，编译器能推断出所调用的方法
        String m2 = ArrayAlg.getMiddle("John", "Q", "Public");
        System.out.println("m2 = " + m2);
        // 但有时候编译器也会提示推断也会出错，
        // double m3 = ArrayAlg.getMiddle(3.14, 100, 0);
    }
}
