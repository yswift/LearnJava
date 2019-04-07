package io.socket;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	private static String savePath = "e:\\";

	private static void receiveFile(Socket socket) {
		byte[] buffer = new byte[1024];

		try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
			// 读取文件名长度
			int len = dis.readShort();
			// 读取文件名
			dis.readFully(buffer, 0, len);
			String fileName = new String(buffer, 0, len, "UTF-8");
			// 建立输出文件
			try (FileOutputStream fos = new FileOutputStream(savePath = fileName)) {
				// 读取文件长度
				len = dis.readInt();
				while (len > 0) {
					int rlen = dis.read(buffer);
					if (rlen == -1) {
						break;
					}
					fos.write(buffer, 0, rlen);
					len -= rlen;
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("start file server");
		try (ServerSocket echoServer = new ServerSocket(8000)) {
			while (true) {
				Socket clientSocket = echoServer.accept();// 阻塞
				System.out.println(clientSocket.getRemoteSocketAddress() + " connect!" + System.currentTimeMillis());

				// 调用receiveFile方法接收文件
				new Thread(() -> { receiveFile(clientSocket); }).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
