package io.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioEchoServer {
	Selector selector;

	void start() throws IOException {
		try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			ServerSocket serverSocket = serverSocketChannel.socket();
			serverSocket.bind(new InetSocketAddress(8000));

			serverSocketChannel.configureBlocking(false);

			selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("服务器已启动，端口号：8000");
			while (true) {
				int readyChannels = selector.select();
				if (readyChannels == 0)
					continue;
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
				while (keyIterator.hasNext()) {
					SelectionKey key = keyIterator.next();
					if (key.isAcceptable()) {
						onAccept(key);
					} else if (key.isReadable()) {
						onRead(key);
					} else if (key.isWritable()) {

					}
					keyIterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void onAccept(SelectionKey key) throws IOException {
		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		// 通过ServerSocketChannel的accept创建SocketChannel实例
		// 完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
		SocketChannel sc = ssc.accept();
		System.out.println("客户端连接," + sc.socket().getRemoteSocketAddress());
		// 设置为非阻塞的
		sc.configureBlocking(false);
		// 注册为读
		sc.register(selector, SelectionKey.OP_READ);
	}

	void onRead(SelectionKey key) throws IOException {
		SocketChannel sc = (SocketChannel) key.channel();
		// 创建ByteBuffer，并开辟一个1K的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 读取请求码流，返回读取到的字节数
		int readBytes = sc.read(buffer);
		// 读取到字节，对字节进行编解码
		if (readBytes <= 0) {
			return;
		}
		// 将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
		buffer.flip();
		// 根据缓冲区可读字节数创建字节数组
		byte[] bytes = new byte[buffer.remaining()];
		// 将缓冲区可读字节数组复制到新建的数组中
		buffer.get(bytes);
		String input = new String(bytes, "UTF-8");
		System.out.println("服务器收到消息：" + input);
		// 发送应答消息
		doWrite(sc, bytes);
	}

	// 异步发送应答消息
	void doWrite(SocketChannel channel, byte[] bytes) throws IOException {
		// 根据数组容量创建ByteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		// 将字节数组复制到缓冲区
		writeBuffer.put(bytes);
		// flip操作
		writeBuffer.flip();
		// 发送缓冲区的字节数组
		channel.write(writeBuffer);
	}

	void onWrite(SelectionKey key) throws IOException {
		ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
		sendbuffer.clear();
		SocketChannel sc = (SocketChannel) key.channel();
		sc.write(sendbuffer);
	}

	public static void main(String[] args) throws IOException {
		NioEchoServer server = new NioEchoServer();
		server.start();
	}

}
