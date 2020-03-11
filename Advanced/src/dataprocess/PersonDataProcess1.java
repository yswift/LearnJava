package dataprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonDataProcess1 {
    // 数据处理工具类，
    // 请补充完成下列方法

    // 获取给定参数 persons 中所有姓李的人
    // 返回 person 的列表
    public static List<Person> getLi(Person[] persons) {
        ArrayList<Person> ps = new ArrayList<>();
        // 从这里开始编写你的代码
        return ps;
    }

    // 获取给定参数 persons 中所有男性
    // 返回 person 的列表
    public static List<Person> getMen(Person[] persons) {
        ArrayList<Person> ps = new ArrayList<>();
        // 从这里开始编写你的代码
        return ps;
    }

    // 获取给定参数 persons 中所有年龄在40-49岁中的所有人
    // 返回 person 的列表
    public static List<Person> getAge40To49(Person[] persons) {
        ArrayList<Person> ps = new ArrayList<>();
        // 从这里开始编写你的代码
        return ps;
    }

    // 获取给定参数 persons 中所有身高在 1.7m 以上的人
    // 返回 person 的列表
    public static List<Person> getThan17(Person[] persons) {
        ArrayList<Person> ps = new ArrayList<>();
        // 从这里开始编写你的代码
        return ps;
    }

    // 获取给定参数 persons 中所有人的平均身高
    // 返回 person 的列表
    public static double getAvgHeight(Person[] persons) {
        // 从这里开始编写你的代码
        return 0;
    }

    // 获取给定参数 persons 身高高于平均身高的人
    // 返回 person 的列表
    public static List<Person> getThanAvgHeight(Person[] persons) {
        ArrayList<Person> ps = new ArrayList<>();
        // 从这里开始编写你的代码
        // 提示，用getAvgHeight得到平均身高，再找出符合条件的人
        return ps;
    }

    // 按姓名排序
    public static void sortByName(Person[] persons) {
        // 从这里开始编写你的代码
    }

    // 按身高排序
    public static void sortByHight(Person[] persons) {
        // 从这里开始编写你的代码
    }

    // 按年龄的逆序排序
    public static void sortByAge(Person[] persons) {
        // 从这里开始编写你的代码
    }

    // 按性别分组，把给定的person按性别分组
    public static Map<String, List<Person>> groupByGender(Person[] persons) {
        Map<String, List<Person>> group = new HashMap<>();
        // 从这里开始编写你的代码
        return group;
    }

    // 按姓分组，每种姓分一组
    public static Map<String, List<Person>> groupByLastName(Person[] persons) {
        Map<String, List<Person>> group = new HashMap<>();
        // 从这里开始编写你的代码
        return group;
    }
}
