package io.webapi.eol;

import com.google.gson.Gson;
import io.HttpTools;

import java.io.IOException;

public class SchoolInfoTools {
    public static SchoolInfo getSchoolInfo(String no) throws IOException {
        String url = "https://static-data.eol.cn/www/school/" + no + "/info.json";
        String json = HttpTools.doGetString(url);
        Gson gson = new Gson();
        SchoolInfo info = gson.fromJson(json, SchoolInfo.class);
        return info;
    }
}
