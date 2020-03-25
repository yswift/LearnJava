package io.webapi.java11api;

import com.google.gson.Gson;
import io.HttpTools;
import io.webapi.eol.SchoolInfo;
import io.webapi.eol.SchoolInfoTools;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SchoolInfoForNewApi {
    // 使用Java11的http API 实现 SchoolInfoTools 类的功能
    public SchoolInfo getSchoolInfo(String no) throws IOException, InterruptedException {
        String url = "https://static-data.eol.cn/www/school/" + no + "/info.json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        SchoolInfo info = gson.fromJson(json, SchoolInfo.class);
        return info;
    }

    @Test
    public void test() throws IOException, InterruptedException {
        SchoolInfo info = getSchoolInfo("355");
        System.out.println(info);
    }
}
