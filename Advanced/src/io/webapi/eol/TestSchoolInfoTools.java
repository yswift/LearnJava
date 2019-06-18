package io.webapi.eol;

import java.io.IOException;

public class TestSchoolInfoTools {
    public static void main(String[] args) throws IOException {
        SchoolInfo info = SchoolInfoTools.getSchoolInfo("355");
        System.out.println(info);
    }
}
