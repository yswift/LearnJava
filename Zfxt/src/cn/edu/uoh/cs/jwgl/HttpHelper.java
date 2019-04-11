package cn.edu.uoh.cs.jwgl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {
	public static String getHtml(String urlString, String cookie, String referer, String encoding) throws IOException {
		try {
			URL url = new URL(urlString);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			// 设置头信息
			http.setRequestProperty("Cookie", cookie);
			http.setRequestProperty("Referer", referer);
						
			InputStream is = http.getInputStream();
			String s = readToEnd(is, encoding);
			return s;
		} finally {
		}
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

}
