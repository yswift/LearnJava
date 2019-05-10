package common;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Stream 案例2
public class StreamE2 {
    // 生成count个随机的学生信息
    List<Student> newStudents(int count) {
        return Stream.generate(Student::new).limit(count).collect(Collectors.toList());
    }

    void print(List<Student> list) {
        list.forEach(System.out::println);
    }

    // 输出年龄大于20的学生
    void printAgeThan20(List<Student> list) {
        System.out.println("出年龄大于20的学生");
        list.stream().filter(s -> s.age > 20).forEach(System.out::println);
    }

    // 输出年龄大于19学生
    void printAgeThan19(List<Student> list) {
    }

    // 输出专业是计科的学生
    void printMajorIsComputer(List<Student> list) {
        System.out.println("输出专业是计科的学生");
        list.stream().filter(s -> "计科".equals(s.major)).forEach(System.out::println);
    }

    // 输出专业是通信的学生的姓名
    void printNameOfMajorIsComputer(List<Student> list) {
        System.out.println("输出专业是计科的学生的姓名");
        list.stream().filter(s -> "计科".equals(s.major))
                .map(s -> s.name)
                .forEach(System.out::println);
    }

    // 输出专业是电气的学生
    void printMajorIsDq(List<Student> list) {
    }

    // 输出专业是电气的学生的姓名
    void printNameOfMajorIsDq(List<Student> list) {
    }

    // 按专业分组
    void groupMajor(List<Student> list) {
        System.out.println("按专业分组");
        Map<String, List<Student>> byMajor = list.stream().collect(Collectors.groupingBy(s -> s.major));
        byMajor.forEach((key, value) -> {
            System.out.println("专业: " + key);
            value.forEach(System.out::println);
        });
    }

    // 按年龄分组
    void groupAge(List<Student> list) {
    }

    // 按专业分组, 输出各专业人数
    void countGroupMajor(List<Student> list) {
        System.out.println("按专业分组, 输出各专业人数");
        Map<String, Long> byMajor = list.stream().collect(Collectors.groupingBy(
                s->s.major, Collectors.counting()));
        byMajor.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    // 按年龄分组, 输出各年龄段人数
    void countGroupAge(List<Student> list) {
    }

    // 用逗号拼接姓名
    void joinName(List<Student> list) {
        System.out.println("用逗号拼接姓名");
        String joined = list.stream().map(s -> s.name).collect(Collectors.joining(",", "[", "]"));
        System.out.println(joined);
    }

    // 用逗号拼接专业，输出时去掉重复专业
    void joinMajor(List<Student> list) {
    }

    public static void main(String[] args) {
        StreamE2 e2 = new StreamE2();
        List<Student> list = e2.newStudents(10);
        e2.print(list);

        e2.printAgeThan20(list);
        e2.printMajorIsComputer(list);
        e2.printNameOfMajorIsComputer(list);

        e2.groupMajor(list);
        e2.countGroupMajor(list);
        e2.joinName(list);
    }
}


class Student {
    static int id = 1;
    static String[] majors = {
            "计科","机械","电气","通信","信息安全"
    };
    // 学号
    String no;
    // 姓名
    String name;
    // 专业
    String major;
    // 年龄
    int age;

    Student() {
        Random r = new Random();
        // 使用随机值初始化类
        no = String.format("20%02d%02d%06d", 10+r.nextInt(10), r.nextInt(10), r.nextInt(200));
        name = "学生" + id++;
        major = majors[r.nextInt(majors.length)];
        age = 18 + r.nextInt(4);
    }

    @Override
    public String toString() {
        return "Student{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", age=" + age +
                '}';
    }
}