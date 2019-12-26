package cn.edu.uoh.cs.jwgl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取学生成绩类
 */
public class StudentScoreFetcher extends Fetcher {
    final String url;

    public StudentScoreFetcher(String userId, String userName, String cookie) {
        super(userId, userName, cookie);
        url = JwglUrl.HOST + "xscjcx.aspx?xh="+ userId + "&xm=" + userName + "&gnmkdm=N121617";
    }

    public String fetch() throws IOException {
        Map<String, String> parameter = createParameter();
        HttpURLConnection http = HttpHelper.doPost(url, url, parameter, cookie);

        // 根据ResponseCode判断连接是否成功
        int responseCode = http.getResponseCode();
        System.out.println("responseCode = " + responseCode);
        // 判断提交数据是否成功，成功返回200
        if (responseCode != 200) {
            System.out.println("获取学生成绩失败");
            return "获取学生成绩失败";
        }
        InputStream is = http.getInputStream();
        String html = HttpHelper.readToEnd(is, JwglUrl.Encoding);
        System.out.println("获取学生成绩, html = " + html);
        return html;
    }

    // 获取，添加请求参数，返回构造好的请求参数
    private Map<String, String> createParameter() throws IOException {
        // 先使用 get 方法读取url， 获取 __VIEWSTATE 的值
        // 以及添加 post 请求参数
        Map<String, String> parameter = new HashMap<>();

        String html = HttpHelper.getHtml(url, cookie, JwglUrl.HOST, JwglUrl.Encoding);
        Document doc = Jsoup.parse(html);
        Elements es = doc.select("#Form1 input[type=hidden]");
        if (es == null || es.size() < 1) {
            return parameter;
        }
        for (Element e : es) {
            parameter.put(e.attr("name"), e.val());
        }
        parameter.put("__EVENTTARGET", "");
        parameter.put("__EVENTARGUMENT", "");
        parameter.put("hidLanguage", "");
        parameter.put("ddlXN", "");
        parameter.put("ddlXQ", "");
        parameter.put("ddl_kcxz", "");
        parameter.put("btn_zcj", "历年成绩");
        return parameter;
    }
}
