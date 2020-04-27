package regex;

import org.junit.Test;

import static org.junit.Assert.*;

public class UTRegexTest {
    RegexTest regexTest = new RegexTest();

    @Test
    public void checkNo() {
        assertTrue("学号: 201812345678", regexTest.checkNo("201812345678"));
        assertFalse("学号: 2018123456789", regexTest.checkNo("2018123456789"));
        assertFalse("学号: 20181234567a", regexTest.checkNo("20181234567a"));
        assertFalse("学号: 21181234567a", regexTest.checkNo("211812345678"));
    }

    @Test
    public void checkTel() {
        assertTrue("电话: 13908731234", regexTest.checkTel("13908731234"));
        assertFalse("电话: 139087312345", regexTest.checkTel("139087312345"));
        assertFalse("电话: 139D8731234", regexTest.checkTel("139D8731234"));
        assertFalse("电话: 23908731234", regexTest.checkTel("23908731234"));
    }

    @Test
    public void checkClassNo() {
        assertEquals("教学班号: (2019-2020-2)-52040161-20090007-1",
                "20090007",
                regexTest.checkClassNo("(2019-2020-2)-52040161-20090007-1"));

        assertEquals("教学班号: (2019-2020-2)-52040161-20090012-1",
                "20090012",
                regexTest.checkClassNo("(2019-2020-2)-52040161-20090012-1"));

        assertNull("教学班号: (20191-2020-2)-52040161-20090007-1",
                regexTest.checkClassNo("(20191-2020-2)-52040161-20090007-1"));

        assertNull("教学班号: (2019-2020-3)-52040161-20090007-1",
                regexTest.checkClassNo("(2019-2020-3)-52040161-20090007-1"));

        assertNull("教学班号: (2019-2020-2)-52040161a-20090007-1",
                regexTest.checkClassNo("(2019-2020-2)-52040161a-20090007-1"));

        assertNull("教学班号: (2019-2020-1)-52040161-200900A7-1",
                regexTest.checkClassNo("(2019-2020-1)-52040161-200900A7-1"));
    }
}
