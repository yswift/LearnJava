package cn.edu.uoh.cs.jwgl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpHelper {
    public static String getHtml(String urlString, String cookie, String referer, String encoding) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        // 设置头信息
        http.setRequestProperty("Cookie", cookie);
        http.setRequestProperty("Referer", referer);

        InputStream is = http.getInputStream();
        return readToEnd(is, encoding);
    }

    public static String readToEnd(InputStream is, String encoding) throws IOException {
        byte[] bs = readToEnd(is);
        return new String(bs, encoding);
    }

    public static byte[] readToEnd(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        is.close();
        return bos.toByteArray();
    }

    public static HttpURLConnection doPost(String url, String referer, Map<String, String> requestParameter, String cookie) throws IOException {
        URL uurl = new URL(url);
        // 打开和URL之间的连接
        HttpURLConnection http = (HttpURLConnection) uurl.openConnection();
        //设置参数
        http.setDoOutput(true);  //需要输出
        http.setDoInput(true);   //需要输入
        http.setUseCaches(false);   //不允许缓存
        http.setRequestMethod("POST");   //设置POST方式连接
        //设置请求属性
        String parameter = buildParameter(requestParameter);
        System.out.println("post + " + url + "\nparameter: " + parameter);
        http.setRequestProperty("Content-Length", String.valueOf(parameter.length()));
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=GB2312");
        http.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        http.setRequestProperty("Cookie", cookie);
        if (referer != null && referer.length() > 0) {
            http.setRequestProperty("Referer", referer);
        }
        //不重定向
        http.setInstanceFollowRedirects(false);
        http.connect();

        OutputStream os = http.getOutputStream();
        os.write(parameter.getBytes(JwglUrl.Encoding));
        os.flush();
        os.close();
        return http;
    }

    public static String buildParameter(Map<String, String> requestParameter) throws UnsupportedEncodingException {
        // 获取URLConnection对象对应的输出流
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> p : requestParameter.entrySet()) {
            sb.append(p.getKey());
            sb.append("=");
            sb.append(URLEncoder.encode(p.getValue(), JwglUrl.Encoding));
            sb.append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

}
