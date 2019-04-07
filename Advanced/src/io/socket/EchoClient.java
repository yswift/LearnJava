package io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	final static String charset = "UTF-8";

	void start() {
		try (Socket socket = new Socket("localhost", 8000)) {
			new Thread(() -> {
				try (BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset))) {
					String inputLine = null;
					while ((inputLine = is.readLine()) != null) {
						System.out.println("From server: " + inputLine);
						if ("bye!".equals(inputLine)) {
							break;
						}
					}
				} catch (IOException e) {
					System.out.println(e);
				}
			}).start();

			try (PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), charset), true)) {
				while (true) {
					Scanner input = new Scanner(System.in);
					String inputLine = input.next();
					os.println(inputLine);
					if (inputLine.equals("exit")) {
						break;
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		client.start();
	}
}
