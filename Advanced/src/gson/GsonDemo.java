package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dataprocess.Person;
import generic.Pair;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GsonDemo {
    @Test
    public void object2Json() {
        // 对象转Json字符串
        Person p = new Person("赵一", "男", "计科", 30, 1.7, 55);
        // 创建Gson对象
        // 为了格式化输出字符串，使用如下方法创建 Gson 对象。
        // 一般情况，直接用构造函数即可，如：Gson gson = new Gson()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // 转换
        String json = gson.toJson(p);
        System.out.println(json);
    }

    @Test
    public void array2Json() {
        // 数组转Json字符串
        Person[] ps = {
                new Person("赵一", "男", "计科", 30, 1.7, 55),
                new Person("钱二", "女", "计科", 33, 1.77, 65),
                new Person("孙三", "女", "计科", 34, 1.73, 75)
        };
        // 创建Gson对象
        // 为了格式化输出字符串，使用如下方法创建 Gson 对象。
        // 一般情况，直接用构造函数即可，如：Gson gson = new Gson()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ps);
        System.out.println(json);
    }

    @Test
    public void list2Json() {
        // list转Json字符串
        List<Person> ps = Arrays.asList(
                new Person("赵一", "男", "计科", 30, 1.7, 55),
                new Person("钱二", "女", "计科", 33, 1.77, 65),
                new Person("孙三", "女", "计科", 34, 1.73, 75)
        );
        // 创建Gson对象
        // 为了格式化输出字符串，使用如下方法创建 Gson 对象。
        // 一般情况，直接用构造函数即可，如：Gson gson = new Gson()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ps);
        System.out.println(json);
    }

    @Test
    public void json2Object() {
        String json = "{\n" +
                "  \"name\": \"赵一\",\n" +
                "  \"gender\": \"男\",\n" +
                "  \"department\": \"计科\",\n" +
                "  \"age\": 30,\n" +
                "  \"height\": 1.7,\n" +
                "  \"weight\": 55.0\n" +
                "}";
        Gson gson = new Gson();
        Person p = gson.fromJson(json, Person.class);
        System.out.println(p);
    }

    @Test
    public void json2Array() {
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"赵一\",\n" +
                "    \"gender\": \"男\",\n" +
                "    \"department\": \"计科\",\n" +
                "    \"age\": 30,\n" +
                "    \"height\": 1.7,\n" +
                "    \"weight\": 55.0\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"孙三\",\n" +
                "    \"gender\": \"女\",\n" +
                "    \"department\": \"计科\",\n" +
                "    \"age\": 34,\n" +
                "    \"height\": 1.73,\n" +
                "    \"weight\": 75.0\n" +
                "  }\n" +
                "]";
        Gson gson = new Gson();
        Person[] p = gson.fromJson(json, Person[].class);
        System.out.println(Arrays.asList(p));
    }

    @Test
    public void json2List() {
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"赵一\",\n" +
                "    \"gender\": \"男\",\n" +
                "    \"department\": \"计科\",\n" +
                "    \"age\": 30,\n" +
                "    \"height\": 1.7,\n" +
                "    \"weight\": 55.0\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"孙三\",\n" +
                "    \"gender\": \"女\",\n" +
                "    \"department\": \"计科\",\n" +
                "    \"age\": 34,\n" +
                "    \"height\": 1.73,\n" +
                "    \"weight\": 75.0\n" +
                "  }\n" +
                "]";
        Gson gson = new Gson();
        // 因为List是泛型类，不能直接使用List<Person>.class作为类型， 因为编译后类型被擦除，
        // 要使用下面的Type类型
        Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        List<Person> p = gson.fromJson(json, type);
        System.out.println(p);
    }

    @Test public void genericObject2Json() {
        Pair<String> pair = new Pair<>("one", "two");
        // 创建Gson对象
        // 为了格式化输出字符串，使用如下方法创建 Gson 对象。
        // 一般情况，直接用构造函数即可，如：Gson gson = new Gson()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(pair);
        System.out.println(json);
    }

    @Test public void genericList2Json() {
        List<Pair<String>> pairs = Arrays.asList(
                new Pair<>("one", "two"),
                new Pair<>("three", "four")
        );
        // 创建Gson对象
        // 为了格式化输出字符串，使用如下方法创建 Gson 对象。
        // 一般情况，直接用构造函数即可，如：Gson gson = new Gson()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(pairs);
        System.out.println(json);
    }

    @Test
    public void json2Generic() {
        String json = "{\n" +
                "  \"first\": \"one\",\n" +
                "  \"second\": \"two\"\n" +
                "}";
        Gson gson = new Gson();
        // 因为Pair是泛型类，不能直接使用Pair<String>.class作为类型， 因为编译后类型被擦除，
        // 要使用下面的Type类型
        Type type = new TypeToken<Pair<String>>() {}.getType();
        Pair<String> p = gson.fromJson(json, type);
        System.out.println(p);
    }

    @Test
    public void json2GenericList() {
        String json = "[\n" +
                "  {\n" +
                "    \"first\": \"one\",\n" +
                "    \"second\": \"two\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"first\": \"three\",\n" +
                "    \"second\": \"four\"\n" +
                "  }\n" +
                "]";
        Gson gson = new Gson();
        // 因为Pair是泛型类，不能直接使用Pair<String>.class作为类型， 因为编译后类型被擦除，
        // 要使用下面的Type类型
        Type type = new TypeToken<List<Pair<String>>>() {}.getType();
        List<Pair<String>> p = gson.fromJson(json, type);
        System.out.println(p);
    }

    @Test
    public void map2Json() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 30);
        map.put("height", 1.7);
        // 创建Gson对象
        // 为了格式化输出字符串，使用如下方法创建 Gson 对象。
        // 一般情况，直接用构造函数即可，如：Gson gson = new Gson()
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);
        System.out.println(json);
    }

    @Test
    public void Json2map() {
        String json = "{\n" +
                "  \"name\": \"张三\",\n" +
                "  \"age\": 30,\n" +
                "  \"height\": 1.7\n" +
                "}";
        Gson gson = new Gson();

        HashMap<String, Object> map = gson.fromJson(json, HashMap.class);
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
        System.out.println(map.get("height"));
    }
}
