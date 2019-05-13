import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStudent {
    @Test
    public void testNew() {
        Student s = new Student("01", "张三", 10);
        assertEquals("01", s.getNo());
    }

    @Test
    public void testToString() {
        Student s = new Student("01", "张三", 10);
        System.out.println(s);
    }

    @Test
    public void testEquals() {
        Student s1 = new Student("01", "shangsan", 10);
        Student s2 = new Student("01", "lisi", 20);

        // 对象相等
        assertEquals(s1, s2);
        // hashcode相等
        assertEquals(s1.hashCode(), s2.hashCode());
    }
}
