package common;

// 正则表达式例子

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
    @Test
    public void testMatch() {
        String content = "I am noob from runoob.com.";
        // 创建Pattern
        Pattern pattern = Pattern.compile(".*runoob.*");
        // 测试是否匹配
        Matcher matcher = pattern.matcher(content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + matcher.matches());
    }

    @Test
    public void getGroup() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
    }

    // 用正则表达式获取身份证号码中的出生日期
    public void getBirthday() {

    }

    // 格式化手机号
    @Test
    public void formatTel() {
        String tel = "13912345678";
        Pattern pattern = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");
        Matcher m = pattern.matcher(tel);
        String newTel = m.replaceAll("$1-$2-$3");
        System.out.println(newTel);
    }

    // 格式化7位电话，为xxx-xxxx
    @Test
    public void formatTel2() {

    }
}
