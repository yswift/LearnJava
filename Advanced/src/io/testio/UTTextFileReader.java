package io.testio;

import io.txtfile.TxtFile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTTextFileReader {
    static String expectedTxt = "实验 输入输出流\r\n" +
            "\r\n" +
            "一、实验目的\r\n" +
            "1、\t掌握java中File类的使用；\r\n" +
            "2、\t熟悉流类库中各种常用流的使用方法。\r\n" +
            "3、\t能够使用流类实现基本的文件读写。\r\n" +
            "二、实验学时\r\n" +
            "2 学时\r\n" +
            "三、实验内容\r\n" +
            "1、编写程序，输入文件夹的绝对路径，输出该文件夹的所有子文件夹。\r\n" +
            "2、编写程序，实现把一个已存在的扩展名为.txt文本文件复制到另一个文本文件中。\r\n" +
            "3、编写程序，从不同的编码文件中正确的读取字符串，已知的字符编码文件有：gbk.txt,utf8.txt,utf16.txt,utf32.txt\r\n";

    @Test
    public void testReadGbk() throws Exception {
        TxtFileReader reader = new TxtFileReader();
        String txt = reader.readTxtFile(TxtFile.GBK, "GBK");
        assertEquals("read gbk", expectedTxt, txt);
    }

    @Test
    public void testReadUTF8() throws Exception {
        TxtFileReader reader = new TxtFileReader();
        String txt = reader.readTxtFile(TxtFile.UTF8, "UTF-8");
        assertEquals("read UTF-8", expectedTxt, txt);
    }

    @Test
    public void testReadUTF16() throws Exception {
        TxtFileReader reader = new TxtFileReader();
        String txt = reader.readTxtFile(TxtFile.UTF16, "UTF-16");
        assertEquals("read UTF-16", expectedTxt, txt);
    }
}
