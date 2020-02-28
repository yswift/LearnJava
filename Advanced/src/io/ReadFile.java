package io;

import io.txtfile.TxtFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFile {
	static void readFile(String fileName, String charset) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(fileName, "r");
		FileChannel rfc = rf.getChannel();
		ByteBuffer byteBuf = ByteBuffer.allocate(100);
		
		CharsetDecoder decoder = Charset.forName(charset).newDecoder();
		CharBuffer charBuf = CharBuffer.allocate(100);
		char[] cs = new char[charBuf.limit()];
		int len = 0;
		while ((len = rfc.read(byteBuf)) != -1) {
			byteBuf.flip();
			decoder.decode(byteBuf, charBuf, false);
			byteBuf.compact();

			charBuf.flip();
			System.out.print(charBuf.toString());
			charBuf.clear();
		}
	}
	
	public static void main(String[] args) throws IOException {
		readFile(TxtFile.UTF8, "UTF-8");
//		readFile("e:\\gbk.txt", "GBK");
	}
}
