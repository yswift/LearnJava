package cn.edu.uoh.cs.jwgl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountTools {
    public final static String UserType教师 = "教师";
    public final static String UserType学生 = "学生";

    // 学号，教工号
    String userId;

    // 用户名
    String userName;

    // 密码
    String password;

    // 登录身份：教师/学生
    String userType;

    // 获取验证码时设置这个值
    String cookie = "";

    boolean isLogin = false;

    public AccountTools() {
    }

    public void init(String userId, String password) {
        this.userId = userId;
        this.password = password;
        userType = userId.length() == 8 ?  UserType教师 : UserType学生;
    }

    /**
     * 获取验证码, 并获取服务器cookie
     *
     * @return 验证码
     * @throws IOException 网络访问异常
     */
    public byte[] getSecretCode() throws IOException {
        URL url = new URL(JwglUrl.SECRET_CODE_URL);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        // 获取返回的Cookie
        cookie = http.getHeaderField("Set-Cookie");
        return HttpHelper.readToEnd(http.getInputStream());
    }


    private Map<String, String> getHiddenInput(String html) {
        Document doc = Jsoup.parse(html);
        Elements es = doc.select("input[type=hidden]");
        HashMap<String, String> hidden = new HashMap<>();
        for (Element e : es) {
            hidden.put(e.attr("name"), e.val());
        }
        // 其它参数
        String[] pns = {"Button1", "lbLanguage"};
        for (String pn : pns) {
            String v = doc.select("#" + pn).val();
            hidden.put(pn, v);
        }
        return hidden;
    }

    private void addRequestParameter(Map<String, String> requestParameter, String code) {
        // 教工号
        requestParameter.put("txtUserName", userId);
        // 密码
        requestParameter.put("TextBox2", password);
        // 验证码
        requestParameter.put("txtSecretCode", code);
        // 身份,教师/学生
        requestParameter.put("RadioButtonList1", userType);
    }

    private String buildParameter(Map<String, String> requestParameter) throws UnsupportedEncodingException {
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

    private void parseName(String html) {
        userName = Jsoup.parse(html).getElementById("xhxm").text();
        Pattern namePattern = Pattern.compile("(.+)老师");
        Matcher m = namePattern.matcher(userName);
        if (m.matches()) {
            userName = m.group(1);
        }
        System.out.println("欢迎你, " + userName);
    }

    private void saveHtmlToFile(String fn, String html) throws IOException {
//        try (FileOutputStream fos = new FileOutputStream(fn)) {
//            fos.write(html.getBytes());
//        }
    }

    /**
     * @param code 验证码
     * @return true，登陆成功； false, 登陆失败
     * @throws UnsupportedOperationException
     * @throws Exception
     */
    public boolean login(String code) throws IOException {
        String html = HttpHelper.getHtml(JwglUrl.HOST, cookie, "", JwglUrl.Encoding);
        // 获取隐藏参数
        Map<String, String> requestParameter = getHiddenInput(html);
        // 添加登陆参数
        addRequestParameter(requestParameter, code);

        String parameter = buildParameter(requestParameter);
        System.out.println("Post");
        System.out.println(parameter);

        URL url = new URL(JwglUrl.LOGIN_URL);
        // 打开和URL之间的连接
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        //设置参数
        http.setDoOutput(true);  //需要输出
        http.setDoInput(true);   //需要输入
        http.setUseCaches(false);   //不允许缓存
        http.setRequestMethod("POST");   //设置POST方式连接
        //设置请求属性
        http.setRequestProperty("Content-Length", String.valueOf(parameter.length()));
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=GB2312");
        http.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        http.setRequestProperty("Cookie", cookie);
        //不重定向
        http.setInstanceFollowRedirects(false);
        http.connect();

        OutputStream os = http.getOutputStream();
        os.write(parameter.getBytes(JwglUrl.Encoding));
        os.flush();
        os.close();

        // 根据ResponseCode判断连接是否成功
        int responseCode = http.getResponseCode();
        System.out.println("responseCode = " + responseCode);
        // 第三步:判断提交数据是否成功，成功返回302
        if (responseCode != 302) {
            System.out.println("登录失败！");
            InputStream is = http.getInputStream();
            html = HttpHelper.readToEnd(is, JwglUrl.Encoding);
            saveHtmlToFile("e:\\r.html", html);
            isLogin = false;
            return isLogin;
        }
        // 如果提交成功，带着Cookie请求重定向的main页面，并获取教师/学生姓名
        try {
            // TODO 这里是或教师名字的，如果是学生，要修改url
            String mainUrl = JwglUrl.MAIN_URL + userId;
            html = HttpHelper.getHtml(mainUrl, cookie, JwglUrl.LOGIN_URL, JwglUrl.Encoding);
            parseName(html);
            saveHtmlToFile("e:\\r2.html", html);
            isLogin = true;
            return isLogin;
        } catch (Exception e) {
            System.out.println("解析html失败！");
            throw e;
        }
    }

    public CourseFetcher createCourseFetcher() {
        if (!isLogin) {
            throw new IllegalArgumentException("必须先登陆，才能获取课表");
        }
        return userType == UserType学生
                ? null
                : new TeacherCourseFetcher(userId, userName,cookie);
    }

    public ExamFetcher createExamFetcher() {
        if (!isLogin) {
            throw new IllegalArgumentException("必须先登陆，才能获取考试/监考表");
        }
        return userType == UserType学生
                ? null
                : new TeacherExamFetcher(userId, userName,cookie);
    }
}
