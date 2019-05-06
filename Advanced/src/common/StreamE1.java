package common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamE1 {
    static void old() {
        for (int i=0; i<10; i++) {
            int r = (int)(Math.random()*10);
            System.out.println(r);
        }
    }

    static void new1() {
        Stream<Integer> generate = Stream.generate(() -> (int)(Math.random()*10));
        generate.limit(10).forEach(System.out::println);
    }

    static void new2() {
        // 生成10个0-10的随机数，并输出
        Stream<Integer> generate = Stream.generate(() -> (int)(Math.random()*10));
        generate.limit(10).forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static List<Integer> new3() {
        Stream<Integer> generate = Stream.generate(() -> (int)(Math.random()*10));
        return generate.limit(10).collect(Collectors.toList());
    }

    static void distinct() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();

        list.stream().distinct().forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static void filter() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // 输出大于5的数
        list.stream().filter(x -> x >= 5).forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static void map() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // 输出 x的平方
        list.stream().map(x -> x*x).forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static void flatMap() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // 输出x 和 x的平方
        list.stream().flatMap(x -> Stream.of(x, x*x)).forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static void peek() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // 在输出x前，先输出2*x
        list.stream().peek(x -> System.out.print(2*x + "  ")).forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static void skip() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // 跳过前4个数
        list.stream().skip(4).forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static void sort1() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // 排序
        list.stream().sorted().forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }

    static void sort2() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // 逆序排序
        list.stream().sorted((x1, x2) -> x2-x1).forEach(x -> System.out.print(x + "  "));
        System.out.println();
    }


    static void reduce() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // reduce 求和
        int s = list.stream().reduce((sum, x) -> x+sum).get();
        System.out.println("sum = " + s);
    }

    static void reduce2() {
        List<Integer> list = new3();
        list.forEach(x -> System.out.print(x + "  "));
        System.out.println();
        // reduce 求积
        int s = list.stream().reduce(1, (f, x) -> x*f);
        System.out.println("积 = " + s);
    }

    public static void main(String[] args) {
        sort1();
    }
}
