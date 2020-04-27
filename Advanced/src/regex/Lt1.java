package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lt1 {
    public static void main(String[] args) {
        String content = "I am noob from runoob.com.";
        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("2223bb");
        boolean f = m.matches();   //匹配整个字符串
//        int s = m.start();   //返回0,原因相信大家也清楚了
//        int e =m.end();   //返回6,原因相信大家也清楚了,因为matches()需要匹配所有字符串
        String g = m.group();   //返回2223bb
    }
}
