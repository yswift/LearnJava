package dataprocess;

import org.junit.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// PersonDataProcess1 的单元测试类，编写的代码要能通过这个单元测试
public class UTPersonDataProcess1 {
    Person[] persons = PersonFactory.persons;

    @Test
    public void testGetLi() {
        List<Person> ps = PersonDataProcess1.getLi(persons);
        assertEquals("姓李人数", 3, ps.size());
        boolean allLi = ps.stream().allMatch(p -> p.getName().startsWith("李"));
        assertTrue("都姓李", allLi);
    }

    @Test
    public void testGetMen() {
        List<Person> ps = PersonDataProcess1.getMen(persons);
        assertEquals("男性人数", 5, ps.size());
        boolean allMatch = ps.stream().allMatch(p -> p.getGender().equals("男"));
        assertTrue("都是男性", allMatch);
    }

    @Test
    public void testGetAge40To49() {
        List<Person> ps = PersonDataProcess1.getAge40To49(persons);
        assertEquals("年龄40-49人数", 4, ps.size());
        boolean allMatch = ps.stream().allMatch(p -> p.getAge() >= 40 && p.getAge() < 50);
        assertTrue("年龄40-49", allMatch);
    }

    @Test
    public void testGetThan17() {
        List<Person> ps = PersonDataProcess1.getThan17(persons);
        assertEquals("身高>1.7m人数", 6, ps.size());
        boolean allMatch = ps.stream().allMatch(p -> p.getHeight() >= 1.7);
        assertTrue("身高>1.7m", allMatch);
    }

    @Test
    public void testAvgAge() {
        double avg = PersonDataProcess1.getAvgHeight(persons);
        double sum = Stream.of(persons).mapToDouble(p -> p.getHeight()).sum();
        // 1.738
        assertEquals("平均身高", sum/ persons.length, avg, 0.001);
    }

    @Test
    public void testGetThanAvgHeight() {
        List<Person> ps = PersonDataProcess1.getThanAvgHeight(persons);
        assertEquals("身高>平均值人数", 3, ps.size());
        boolean allMatch = ps.stream().allMatch(p -> p.getHeight() > 1.738);
        assertTrue("身高>平均值", allMatch);
    }

    @Test
    public void testSortByName() {
        PersonDataProcess1.sortByName(persons);
        String n = persons[0].getName();
        for (Person p : persons) {
            String tip = "名字排序：" + n + "<=" + p.getName();
            assertTrue(tip, n.compareTo(p.getName()) <= 0);
        }
    }

    @Test
    public void testSortByHight() {
        PersonDataProcess1.sortByHight(persons);
        double h = persons[0].getHeight();
        for (Person p : persons) {
            String tip = "身高排序：" + h + "<=" + p.getHeight();
            assertTrue(tip, h <= p.getHeight());
        }
    }

    @Test
    public void testSortByAge() {
        PersonDataProcess1.sortByHight(persons);
        int h = persons[0].getAge();
        for (Person p : persons) {
            String tip = "年龄排序：" + h + "<=" + p.getAge();
            assertTrue(tip, h <= p.getAge());
        }
    }

    @Test
    public void testGroupByGender() {
        Map<String, List<Person>> gs = PersonDataProcess1.groupByGender(persons);
        assertEquals("性别分组", 2, gs.size());
        for (Map.Entry<String, List<Person>> e : gs.entrySet()) {
            boolean allMatch = e.getValue().stream().allMatch(p -> p.getGender().equals(e.getKey()));
            assertTrue("都是" + e.getKey(), allMatch);
        }
    }

    @Test
    public void testGroupByLastName() {
        Map<String, List<Person>> gs = PersonDataProcess1.groupByGender(persons);
        assertEquals("姓分组", 4, gs.size());
        for (Map.Entry<String, List<Person>> e : gs.entrySet()) {
            boolean allMatch = e.getValue().stream().allMatch(p -> p.getGender().startsWith(e.getKey()));
            assertTrue("都姓" + e.getKey(), allMatch);
        }
    }
}
