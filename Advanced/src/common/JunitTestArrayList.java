package common;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JunitTestArrayList {
    ArrayList<String> list = new ArrayList<>();

    @Before
    public void before() {
        list.clear();
    }

    @Test
    public void testAdd() {
        String a = "add";
        list.add(a);
        assertEquals("数量为1", 1, list.size());
        assertTrue("包括a", list.contains(a));
    }

    @Test
    public void testGet() {
        String a = "add";
        list.add(a);
        String b = list.get(0);
        assertSame("a==b", a, b);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetExcepton() {
        list.get(0);
    }

}
