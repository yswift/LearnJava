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
        http.setConnectTimeout(1000);
        http.setReadTimeout(1000);
        // 获取返回的Cookie
        cookie = http.getHeaderField("Set-Cookie");
        return HttpHelper.readToEnd(http.getInputStream());
    }

    /**
     * 识别验证码
     * @param img 验证码图像
     * @return 验证码
     * @throws IOException
     */
    public String verifyCode(byte[] img) throws IOException {
        String res = HttpHelper.postBase64(JwglUrl.VERIFY_CODE, img);
        System.out.println("res="+res);
        Pattern p = Pattern.compile("\"code\":\"(.{4})\"");
        Matcher m = p.matcher(res);
        return m.find() ? m.group(1) : "";
    }


    private HashMap<String, String> getHiddenInput(Document doc) {
        // 获取隐藏参数
        Elements es = doc.select("input[type=hidden]");
        HashMap<String, String> requestParameter = new HashMap<>();
        for (Element e : es) {
            requestParameter.put(e.attr("name"), e.val());
        }
        return requestParameter;
    }

    private Map<String, String> creatParameter(String code) throws IOException {
        String html = HttpHelper.getHtml(JwglUrl.HOST, cookie, "", JwglUrl.Encoding);
        Document doc = Jsoup.parse(html);
        // 获取隐藏参数
        HashMap<String, String> requestParameter = getHiddenInput(doc);
        // 其它参数
        String[] pns = {"Button1", "lbLanguage"};
        for (String pn : pns) {
            String v = doc.select("#" + pn).val();
            requestParameter.put(pn, v);
        }
        // 教工号
        requestParameter.put("txtUserName", userId);
        // 密码
        requestParameter.put("TextBox2", password);
        // 验证码
        requestParameter.put("txtSecretCode", code);
        // 身份,教师/学生
        requestParameter.put("RadioButtonList1", userType);
        return requestParameter;
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
        // 获取,添加登陆参数
        Map<String, String> requestParameter = creatParameter(code);
        // post 请求
        HttpURLConnection http = HttpHelper.doPost(JwglUrl.LOGIN_URL, null, requestParameter, cookie);
        // 根据ResponseCode判断连接是否成功
        int responseCode = http.getResponseCode();
        System.out.println("responseCode = " + responseCode);
        // 第三步:判断提交数据是否成功，成功返回302
        if (responseCode != 302) {
            System.out.println("登录失败！");
            InputStream is = http.getInputStream();
            String html = HttpHelper.readToEnd(is, JwglUrl.Encoding);
            saveHtmlToFile("e:\\r.html", html);
            isLogin = false;
            return isLogin;
        }
        // 如果提交成功，带着Cookie请求重定向的main页面，并获取教师/学生姓名
        try {
            if (userType == UserType教师) {
                String mainUrl = JwglUrl.JS_MAIN_URL + userId;
                fetchName(mainUrl, "(.+)老师");
            } else {
                String mainUrl = JwglUrl.XS_MAIN_URL + userId;
                fetchName(mainUrl, "(.+)同学");
            }
            isLogin = true;
            return isLogin;
        } catch (Exception e) {
            System.out.println("解析html失败！");
            throw e;
        }
    }

    void fetchName(String url, String pattern) throws IOException {
        String html = HttpHelper.getHtml(url, cookie, JwglUrl.LOGIN_URL, JwglUrl.Encoding);
        saveHtmlToFile("e:\\r2.html", html);
        userName = Jsoup.parse(html).getElementById("xhxm").text();
        Pattern namePattern = Pattern.compile(pattern);
        Matcher m = namePattern.matcher(userName);
        if (m.matches()) {
            userName = m.group(1);
        }
        System.out.println("欢迎你, " + userName);
    }

    public CourseFetcher createCourseFetcher() {
        if (!isLogin) {
            throw new IllegalArgumentException("必须先登陆，才能获取课表");
        }
        return userType == UserType学生
                ? new StudentCourseFetcher(userId, userName, cookie)
                : new TeacherCourseFetcher(userId, userName, cookie);
    }

    public ExamFetcher createExamFetcher() {
        if (!isLogin) {
            throw new IllegalArgumentException("必须先登陆，才能获取考试/监考表");
        }
        return userType == UserType学生
                ? new StudentExamFetcher(userId, userName, cookie)
                : new TeacherExamFetcher(userId, userName, cookie);
    }

    public StudentScoreFetcher createScoreFetcher() {
        return new StudentScoreFetcher(userId, userName, cookie);
    }
}
