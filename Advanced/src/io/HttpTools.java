package io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class HttpTools {
	private static final HashMap<String, String> EmptyProps = new HashMap<String, String>();
	
	public static String doGetString(String url) throws IOException {
		return doGetString(url, EmptyProps, "UTF-8");
	}
	
	public static String doGetString(String url, String charset) throws IOException {
		return doGetString(url, EmptyProps, charset);
	}
	
	public static String doGetString(String url, Map<String, String> props) throws IOException {
		return doGetString(url, props, "UTF-8");
	}

	public static String doGetString(String url, Map<String, String> props, String charset) throws IOException {
		InputStream is = doGet(url, props);
		String s = readString(is, charset);
		is.close();
		return s;
	}
	
	public static byte[] doGetBytes(String url) throws IOException {
		return doGetBytes(url, new HashMap<String, String>());
	}

	public static byte[] doGetBytes(String url, Map<String, String> props) throws IOException {
		InputStream is = doGet(url, props);
		byte[] s = readBytes(is);
		is.close();
		return s;
	}
	
	public static InputStream doGet(String url) throws IOException {
		return doGet(url, EmptyProps);
	}
	
	public static InputStream doGet(String url, Map<String, String> props) throws IOException {
		// 把参数附加到url上
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> e : props.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(e.getKey()).append("=").append(e.getValue());
		}
		if (sb.length() > 0) {
			url += "?" + sb.toString();
		}
		URL uurl = new URL(url);
        URLConnection urlc = uurl.openConnection();
        InputStream is = urlc.getInputStream();
        // 检查获取的结果是否是被压缩过的
        if ("gzip".equals(urlc.getContentEncoding())) {
            // 如果是用GZIPInputStream包裹节压缩
            is = new GZIPInputStream(is);
        }
        return is;
	}
	
	// POST
	public static String doPostString(String url) throws IOException {
		return doPostString(url, new HashMap<String, String>(), "UTF-8");
	}
	
	public static String doPostString(String url, String charset) throws IOException {
		return doPostString(url, new HashMap<String, String>(), charset);
	}
	
	public static String doPostString(String url, Map<String, String> props) throws IOException {
		return doPostString(url, props, "UTF-8");
	}

	public static String doPostString(String url, Map<String, String> props, String charset) throws IOException {
		InputStream is = doPost(url, props);
		String s = readString(is, charset);
		is.close();
		return s;
	}
	
	public static byte[] doPostBytes(String url) throws IOException {
		return doPostBytes(url, new HashMap<String, String>());
	}

	public static byte[] doPostBytes(String url, Map<String, String> props) throws IOException {
		InputStream is = doPost(url, props);
		byte[] s = readBytes(is);
		is.close();
		return s;
	}
	
	public static InputStream doPost(String url, Map<String, String> props) throws IOException {
		URL uurl = new URL(url);
        URLConnection urlc = uurl.openConnection();
        for (Map.Entry<String, String> e : props.entrySet()) {
        	urlc.setRequestProperty(e.getKey(), e.getValue());
		}
        // 发送POST请求必须设置如下两行
        urlc.setDoOutput(true);
        urlc.setDoInput(true);
        InputStream is = urlc.getInputStream();
        // 检查获取的结果是否是被压缩过的
        if ("gzip".equals(urlc.getContentEncoding())) {
            // 如果是用GZIPInputStream包裹节压缩
            is = new GZIPInputStream(is);
        }
        return is;
	}
	
	/**
	 * 从流中读取所有内容，直到流结束，并转换为String格式。
	 * 
	 * @param is
	 *            输入流
	 * @return 读取到字符串
	 * @throws IOException
	 */
	private static String readString(InputStream is, String charset) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		readTo(is, bos);
		return bos.toString(charset);
	}
	
	/**
	 * 流中读取所有内容，直到流结束。
	 * @param is
	 * @return byte数组
	 * @throws IOException
	 */
	private static byte[] readBytes(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		readTo(is, bos);
		return bos.toByteArray();
	}

	/**
	 * 从流中读取所有内容，并写入到另外的流中。
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @throws IOException
	 */
	private static void readTo(InputStream is, OutputStream os) throws IOException {
		// 定义读取的缓冲区
		final int len = 1024;
		byte data[] = new byte[len];
		while (true) {
			int rlen = is.read(data);
			if (rlen == -1) {
				break;
			}
			os.write(data, 0, rlen);
		}
	}

}
