package cn.edu.uoh.cs.jwgl;

public interface JwglUrl {
	// 教务系统首页
	public static final String HOST = "http://jwgl.uoh.edu.cn/";
	// 验证码页面
//	public static final String SECRET_CODE_URL = HOST + "CheckCode.aspx";
	public static final String SECRET_CODE_URL = "http://jwxt.njupt.edu.cn/CheckCode.aspx";
	// 教务系统登录页
	public static final String LOGIN_URL = HOST + "Default2.aspx";
	// 教务系统主页，菜单页面, todo 学生的不一样
	public static final String JS_MAIN_URL = HOST + "js_main.aspx?xh=";
	public static final String XS_MAIN_URL = HOST + "xs_main.aspx?xh=";

	public static final String Gnmkdm = "&gnmkdm=N122304";
	
	public static final String Encoding = "GB2312";

	// 验证码识别
	String VERIFY_CODE = "http://192.168.31.82:5000/verify/base64";
//	String VERIFY_CODE = "http://10.10.50.54:5000/verify/base64";
}
