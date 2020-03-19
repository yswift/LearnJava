package io.txtfile;

public class TxtFile {
	public static final String GBK = getFullName("GBK.TXT");
	public static final String UTF8 = getFullName("UTF8.TXT");
	public static final String UTF16 = getFullName("UTF16.TXT");
	public static final String UTF32 = getFullName("UTF32.TXT");
	
	// 获取用于测试的txt文件的完整文件名.
	public static String getFullName(String fn) {
		return System.getProperty("user.dir") + "\\Advanced\\src\\io\\txtfile\\" + fn;
	}

}
