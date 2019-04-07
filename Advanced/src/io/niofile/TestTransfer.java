package io.niofile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TestTransfer {
	public static void main(String[] args) throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		// 下面这两个方法等价
		toChannel.transferFrom(fromChannel, position, count);
		
		// fromChannel.transferTo(position, count, toChannel);
	}
}
