package io.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		System.out.println("start server");
		try (ServerSocket echoServer = new ServerSocket(8000)) {
			while (true) {
				Socket clientSocket = echoServer.accept();// 阻塞
				System.out.println(clientSocket.getRemoteSocketAddress() + " connect!" + System.currentTimeMillis());

				// 子线程负责执行与client socket 交互的操作。
				Thread thread = new Thread(new MessageHandler(clientSocket));
				thread.start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
