package io.socket;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileClient {
	public static void main(String[] args) {
		String fn = "e:\\utf8.txt";
		System.out.println("连接到服务器");
		try (Socket socket = new Socket("localhost", 8000); FileInputStream fis = new FileInputStream(fn)) {

			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			// 发送文件名
			byte[] fnbs = (fn + "2").getBytes("UTF-8");
			dos.writeShort(fnbs.length);
			dos.write(fnbs);

			// 发送文件长度
			File f = new File(fn);
			dos.writeInt((int) f.length());

			System.out.println("开始发送数据");
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, len);
			}
			dos.close();
			System.out.println("发送完成");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
