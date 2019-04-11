package cn.edu.uoh.cs.jwgl;

public interface JwglUrl {
	// 教务系统首页
	public static final String HOST = "http://jwgl.uoh.edu.cn/";
	// 验证码页面
	public static final String SECRET_CODE_URL = HOST + "CheckCode.aspx";
	// 教务系统登录页
	public static final String LOGIN_URL = HOST + "Default2.aspx";
	// 教务系统主页，菜单页面
	public static final String MAIN_URL = HOST + "js_main.aspx?xh=";
	
	public static final String Gnmkdm = "&gnmkdm=N122304";
	
	public static final String Encoding = "GB2312";
}
