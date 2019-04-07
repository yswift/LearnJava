package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
	public static void main(String[] args) throws IOException {
		System.out.println("copy starting");
		RandomAccessFile rf = new RandomAccessFile("e:\\f.otf", "r");
		RandomAccessFile wf = new RandomAccessFile("e:\\t.otf", "rw");
		
		FileChannel rfc = rf.getChannel();
		FileChannel wfc = wf.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		int len = 0;
		while ((len = rfc.read(buf)) != -1) {
			buf.flip();
			wfc.write(buf);
			buf.compact();
		}
		rfc.close();
		wfc.close();
		rf.close();
		wf.close();
		System.out.println("copy finished.");
	}

}
