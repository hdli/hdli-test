package com.example.hdlitest.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-20 15:33
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        readNio("E:\\opt\\app\\log\\fitness_log.log");
//        bufferedReader("E:\\opt\\app\\log\\fitness_log.log");
    }

    /**
     * 普通IO的读取文件方法
     * @param path
     * @throws IOException
     */
    public static void bufferedReader(String path) throws IOException {
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s = bufferedReader.readLine();
        while (s != null){
            System.out.println(s);
            s = bufferedReader.readLine();
        }
    }


    public static void readNio(String path) {
        /**
         * r：以只读的方式打开，调用该对象的任何write（写）方法都会导致IOException异常
         * rw：以读、写方式打开，支持文件的读取或写入。若文件不存在，则创建之。
         * rws：以读、写方式打开，与“rw”不同的是，还要对文件内容的每次更新都同步更新到潜在的存储设备中去。这里的“s”表示synchronous（同步）的意思
         * rwd：以读、写方式打开，与“rw”不同的是，还要对文件内容的每次更新都同步更新到潜在的存储设备中去。
         * 使用“rwd”模式仅要求将文件的内容更新到存储设备中，而使用“rws”模式除了更新文件的内容，还要更新文件的元数据（metadata），
         * 因此至少要求1次低级别的I/O操作
         */
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(path, "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void test(String path) throws IOException {

        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        RandomAccessFile fileRead = null;
        try {
            //用读模式
            fileRead = new RandomAccessFile(path, "r");
            //获得文件长度
            long length = fileRead.length();
            if (length == 0L) {//文件内容为空
                return;
            }
            // 开始位置
            long perSize = length / threadCount;
            long start;
            long end;
            for (int i = 0; i < threadCount; i++) {
                start = i * perSize;
                if (i == threadCount - 1) {
                    end = length - 1;
                } else {
                    end = (i + 1) * perSize - 1;
                }
               executorService.execute(new ReadTask(start,end,new File(path)));
            }
        }finally {

        }
    }

    static class ReadTask implements Runnable{
        private long start;
        private long end;
        private File file;

        public ReadTask(long start, long end, File file) {
            this.start = start;
            this.end = end;
            this.file = file;
        }

        @Override
        public void run() {
            //利用RandomAccessFile.seek()
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                randomAccessFile.seek(start);
                //
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
