package cn.edu.uoh.cs.jwgl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZfSecretCode {
    static final String folder = "E:\\Work\\zfxt\\";

    static void saveToFile(int id, byte[] bytes) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(folder + id + ".jpg")){
            fos.write(bytes);
        }
    }

    public static void main(String[] args) throws IOException {
        AccountTools tools = new AccountTools();
        for (int i=1000; i<10000; i++) {
            byte[] bytes = tools.getSecretCode();
            saveToFile(i, bytes);
        }
    }
}
