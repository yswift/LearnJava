package io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageHandler implements Runnable{
	final static String charset = "UTF-8";
	
    private Socket clientSocket;
    private String name;

	public MessageHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		name = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
	}
    
	@Override
    public void run() {
        try (BufferedReader is=new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), charset));
        		PrintWriter os=new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),charset),true);) {
            //从InputStream当中读取客户端所发送的数据
            String inputLine=null;
            while ((inputLine=is.readLine())!=null){
            	System.out.println(name + ": " + inputLine);
            	if ("exit".equals(inputLine)) {
            		os.println("bye!");
            		break;
            	}
                os.println(inputLine);
            }
            clientSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
