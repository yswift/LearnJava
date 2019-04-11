package cn.edu.uoh.cs.jwgl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

public class Account {
	String cookie = "";

	String userId;

	String userName;

	String password;

	// 登录身份：教师/学生
	String identity = "教师";

	String mainUrl = "";

	public String getViewState() throws IOException {
		String html = HttpHelper.getHtml(JwglUrl.HOST, cookie, "", JwglUrl.Encoding);
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

		// post 参数
		StringBuilder sb = new StringBuilder();
		// 隐藏表单值
		sb.append("__VIEWSTATE=").append(URLEncoder.encode(viewState, JwglUrl.Encoding));
		// 教工号
		sb.append("&txtUserName=").append(URLEncoder.encode(userId, JwglUrl.Encoding));
		// 密码
		sb.append("&TextBox2=").append(URLEncoder.encode(password, JwglUrl.Encoding));
		// 验证码
		sb.append("&txtSecretCode=").append(URLEncoder.encode(code, JwglUrl.Encoding));
		// 身份,教师/学生
		sb.append("&RadioButtonList1=").append(URLEncoder.encode(identity, JwglUrl.Encoding));
		// 其它
		sb.append("&Button1=");
		sb.append("&lbLanguage=");
		sb.append("&hidPdrs=");
		sb.append("&hidsc=");

		String parm = sb.toString();
		System.out.println("Post");
		System.out.println(parm);

		URL url = new URL(JwglUrl.LOGIN_URL);
		// 打开和URL之间的连接
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		//设置参数
		http.setDoOutput(true);  //需要输出
		http.setDoInput(true);   //需要输入
		http.setUseCaches(false);   //不允许缓存
		http.setRequestMethod("POST");   //设置POST方式连接
		//设置请求属性
		http.setRequestProperty("Content-Length", String.valueOf(parm.length()));
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=GB2312");
		http.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		http.setRequestProperty("Cookie", cookie);
		//不重定向
		http.setInstanceFollowRedirects(false);
		http.connect();

		OutputStream os = http.getOutputStream();
		os.write(parm.getBytes(JwglUrl.Encoding));
		os.flush();
		os.close();

		// 根据ResponseCode判断连接是否成功
		int responseCode = http.getResponseCode();
		System.out.println("responseCode = " + responseCode);
		try {
			// 第三步:判断提交数据是否成功，成功返回302
			if (responseCode != 302) {
				System.out.println("登录失败！");
//				InputStream is = http.getInputStream();
//				byte[] s = HttpHelper.readToEnd(is);
//				FileOutputStream fos = new FileOutputStream("e:\\tt\\r.html");
//				fos.write(s);
//				fos.close();
				return false;
			}
			mainUrl = JwglUrl.MAIN_URL + userId;
			// 如果提交成功，带着Cookie请求重定向的main页面，并获取学生姓名
			try {
				String html = HttpHelper.getHtml(mainUrl, cookie, JwglUrl.LOGIN_URL, JwglUrl.Encoding);
//				FileOutputStream fos = new FileOutputStream("e:\\tt\\r2.html");
//				fos.write(html.getBytes());
//				fos.close();
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