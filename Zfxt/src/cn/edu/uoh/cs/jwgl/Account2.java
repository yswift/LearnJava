package cn.edu.uoh.cs.jwgl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

public class Account2 {
	String cookie = "";

	String userId;

	String userName;

	String password;

	// 登录身份：教师/学生
	String identity = "教师";
	
	String mainUrl = "";

	public static String getViewState() throws IOException {
		String html = HttpHelper.getHtml(JwglUrl.HOST, "", "", JwglUrl.Encoding);
		String viewstate = Jsoup.parse(html).select("input[name=__VIEWSTATE]").val();
		return viewstate;
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] getSecretCode() throws IOException {
		try {
			URL url = new URL(JwglUrl.SECRET_CODE_URL);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			// 获取返回的Cookie
			cookie = http.getHeaderField("Set-Cookie");
			byte[] bs = HttpHelper.readToEnd(http.getInputStream());
			return bs;
		} finally {
		}
	}

	/**
	 * 
	 * @param userId
	 *            教工号/学号
	 * @param password
	 *            秘密
	 * @param code
	 *            验证码
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws Exception
	 */
	public boolean login(String userId, String password, String code) throws IOException {
		this.userId = userId;
		this.password = password;
		
		String viewState = getViewState();
		System.out.println("Set-Cookie = " + cookie);
		System.out.println("__VIEWSTATE = " + viewState);
		
		URL url = new URL(JwglUrl.LOGIN_URL);
		// 打开和URL之间的连接
		HttpURLConnection http =  (HttpURLConnection) url.openConnection();
		// 发送POST请求必须设置如下两行
		http.setDoOutput(true);
		http.setDoInput(true);
		// 设置通用的请求属性
		http.setRequestProperty("accept", "*/*");
		http.setRequestProperty("connection", "Keep-Alive");
		http.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		
		http.setRequestProperty("Referer", JwglUrl.HOST);
		http.setRequestProperty("Cookie", cookie.split(";")[0]);
		http.setRequestMethod("POST"); // 设置请求方式  
		
        //http.connect();  

		HashMap<String, String> nameValuePairLogin = new HashMap<>(); 
		// 隐藏表单值
		nameValuePairLogin.put("__VIEWSTATE", viewState);
		// 教工号
		nameValuePairLogin.put("txtUserName", userId);
		// 密码
		nameValuePairLogin.put("TextBox2", password);
		// 验证码
		nameValuePairLogin.put("txtSecretCode", code);
		// 身份,教师/学生
		nameValuePairLogin.put("RadioButtonList1", identity);
		nameValuePairLogin.put("Button1", "");
		nameValuePairLogin.put("lbLanguage", "");
		nameValuePairLogin.put("hidPdrs", "");
		nameValuePairLogin.put("hidsc", "");
		
		// 获取URLConnection对象对应的输出流  
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> p : nameValuePairLogin.entrySet()) {
			sb.append(p.getKey());
			sb.append("=");
//			pw.write(URLEncoder.encode(p.getValue(), JwglUrl.Encoding));
//			sb.append(URLEncoder.encode(p.getValue(), "UTF8"));
			sb.append(URLEncoder.encode(p.getValue(), JwglUrl.Encoding));
			sb.append("&");
		}
		String parm = sb.substring(0, sb.length()-1);
		System.out.println("Post");
		System.out.println(parm);
		
		PrintWriter pw = new PrintWriter(http.getOutputStream());
		pw.write(parm);
		pw.flush();
//		OutputStream os = http.getOutputStream();
//		os.write(parm.getBytes("UTF8"));
//		os.flush();
//		os.close();

     // 根据ResponseCode判断连接是否成功  
        int responseCode = http.getResponseCode();
        System.out.println("responseCode = " + responseCode);
		try {
			// 第三步:判断提交数据是否成功，成功返回302
			if (responseCode != 302) {
				System.out.println("登录失败！");
//				InputStream is = http.getInputStream();
//				System.out.println(HttpHelper.readToEnd(is, JwglUrl.Encoding));
				return false;
			}
			mainUrl = JwglUrl.MAIN_URL + userId;
			// 如果提交成功，带着Cookie请求重定向的main页面，并获取学生姓名
			String html = "";
			try {
				html = HttpHelper.getHtml(mainUrl, cookie, JwglUrl.LOGIN_URL, JwglUrl.Encoding);
				// System.out.println(html);
				userName = Jsoup.parse(html).getElementById("xhxm").text();
				Pattern namePattern = Pattern.compile("(.+)老师");
				Matcher m = namePattern.matcher(userName);
				if (m.matches()) {
					userName = m.group(1);
				}
				System.out.println("欢迎你, " + userName);
				return true;
			} catch (Exception e) {
				System.out.println("解析html失败！");
				throw e;
			}
		} finally {
		}
	}
	
	
}
