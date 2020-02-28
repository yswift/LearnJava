package cn.edu.uoh.cs.jwgl;

import java.io.*;
import java.net.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;
import java.util.zip.GZIPInputStream;

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

    public static String postBase64(String urls, byte[] img) throws IOException {
        URL uurl = new URL(urls);
        URLConnection urlc = uurl.openConnection();

        urlc.setConnectTimeout(1000);
        urlc.setReadTimeout(1000);

        urlc.setRequestProperty("accept", "*/*");
        urlc.setRequestProperty("connection", "Keep-Alive");
        urlc.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
        // 发送POST请求必须设置如下两行
        urlc.setDoOutput(true);
        urlc.setDoInput(true);

        // 获取URLConnection对象对应的输出流
        OutputStreamWriter  out = new OutputStreamWriter (urlc.getOutputStream());
        // 发送请求参数
        Base64.Encoder encoder = Base64.getEncoder();
        String code = encoder.encodeToString(img);
        out.write("base64=");
        out.write(URLEncoder.encode(code, "UTF-8"));
        // flush输出流的缓冲
        out.flush();

        InputStream is = urlc.getInputStream();
        // 检查获取的结果是否是被压缩过的
        if ("gzip".equals(urlc.getContentEncoding())) {
            // 如果是用GZIPInputStream包裹节压缩
            is = new GZIPInputStream(is);
        }
        return readToEnd(is, "UTF-8");
    }

    public static String uploadImage(String urls, byte[] img) throws IOException {
        // 服务器的域名
        String newLine = "\r\n";
        // 定义数据分隔线
        String BOUNDARY = "------52802417230194";
        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置为POST情
        conn.setRequestMethod("POST");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // 设置请求头参数
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        OutputStream out = conn.getOutputStream();

        // 上传文件
        StringBuilder sb = new StringBuilder();
        sb.append(BOUNDARY);
        sb.append(newLine);
        // 文件参数,photo参数名可以随意修改
        sb.append("Content-Disposition: form-data;name=\"photo\";filename=\"file\"" + newLine);
        sb.append("Content-Type:application/octet-stream");
        // 参数头设置完以后需要两个换行，然后才是参数内容
        sb.append(newLine);
        sb.append(newLine);

        // 将参数头的数据写入到输出流中
        out.write(sb.toString().getBytes());

        // 写入图像
        out.write(img);
        // 最后添加换行
        out.write(newLine.getBytes());

        // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
        byte[] end_data = (newLine + BOUNDARY + "--" + newLine).getBytes();
        // 写上结尾标识
        out.write(end_data);
        out.flush();
        out.close();

        return readToEnd(conn.getInputStream(), "UTF-8");
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
