package com.example.hdlitest.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * 从最后一行开始读取
 * @author 李会东
 * @version 1.0
 * @date 2020-1-15 23:08
 */
public class FromEndRF {

    public static void main(String[] args) {
        read("E:\\test\\pc.txt", "UTF-8");
    }

    /**
     * 从最后一行开始读取
     * @param filename 目标文件
     * @param charset  目标文件的编码格式
     */
    public static void read(String filename, String charset) {
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filename, "r");
            long len = rf.length();
            long start = rf.getFilePointer();
            long nextend = start + len - 1;
            String line;
            rf.seek(nextend);
            int c = -1;
            while (nextend > start) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    line = rf.readLine();
                    if (line != null) {
                        System.out.println(new String(line.getBytes(StandardCharsets.ISO_8859_1), charset));
                    } else {
                        System.out.println(line);
                    }
                    nextend--;
                }
                nextend--;
                rf.seek(nextend);
                if (nextend == 0) {
                    // 当文件指针退至文件开始处，输出第一行
                    System.out.println(new String(rf.readLine().getBytes(StandardCharsets.ISO_8859_1), charset));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null){
                    rf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
