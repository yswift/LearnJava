package cn.edu.uoh.cs.springbootdemo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class StringUtil {
    public static boolean isEmpty(String s) {
        return s == null || s.length() < 1;
    }

    public static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return value;
        }
    }
}
