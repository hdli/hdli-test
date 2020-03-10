package com.example.hdlitest.io;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-13 14:48
 */
public class ReadBigFileTest {

    /**
     * 在内存中读取
     * 读取文件行的标准方式是在内存中读取，Guava 和Apache Commons IO都提供了如下所示快速读取文件行的方法：
     *
     * 这种方法带来的问题是文件的所有行都被存放在内存中，当文件足够大时很快就会导致程序抛出OutOfMemoryError 异常
     */
    public static void readMemory(String path){
        //Guava
        try {
            List<String> list = Files.readLines(new File(path), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Apache Commons IO
        try {
            List<String> list = FileUtils.readLines(new File(path), org.apache.commons.io.Charsets.toCharset("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 现在让我们看下这种解决方案——我们将使用java.util.Scanner类扫描文件的内容，一行一行连续地读取：
     * @param path
     * @throws IOException
     */
    public static void readStream(String path) throws IOException {

        try (FileInputStream inputStream = new FileInputStream(path); Scanner sc = new Scanner(inputStream, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同样也可以使用Commons IO库实现，利用该库提供的自定义LineIterator
     * @param path
     * @throws IOException
     */
    public static void readIoCommon(String path) throws IOException {
        try (LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                // do something with line
            }
        }
        // LineIterator.closeQuietly(it);
    }


    /**
     * 读文件
     * @param path
     * @param handleLine
     * @throws IOException
     */
    public static void readJDK(String path, HandleLine1 handleLine) throws IOException {
        try (FileReader in = new FileReader(path); BufferedReader br = new BufferedReader(in)) {
            // 行号
            int lineNumber = 0;
            String line = null;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                handleLine.handle(lineNumber, line);
            }
        }
    }
    public static void readJDK2(String path,HandleLine1 handleLine) throws IOException{
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(in)) {
            // 行号
            int lineNumber = 0;
            String line = null;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                handleLine.handle(lineNumber, line);
            }
        }
    }

    /**
     * 写文件
     * @param path
     * @param content
     * @throws IOException
     */
    public static void writerJdk(String path,List<String> content) throws IOException {
        try (FileWriter writer = new FileWriter(path, true)) {
            for (String s : content) {
                writer.write(s);
                //每写入一个行就换一行
                writer.write("\r\n");
            }
        }
    }
    public static void writerJdk2(String path,List<String> content) throws IOException {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try{
            File file = new File(path);

            if(!file.exists()){
                //如果文件不存在，就创建该文件
                boolean b = file.createNewFile();
                //首次写入获取
                fos = new FileOutputStream(file);
            }else {
                //如果文件已存在，那么就在文件末尾追加写入
                //这里构造方法多了一个参数true,表示在文件末尾追加写入
                fos = new FileOutputStream(file,true);
            }
            //指定以UTF-8格式写入文件
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            for (String s : content) {
                osw.write(s);
                //每写入一个Map就换一行
                osw.write("\r\n");
            }
        }finally {
            if (osw != null){
                osw.close();
            }
            if (fos != null){
                fos.close();
            }
        }
    }


}

interface HandleLine1{

    void handle(int lineNumber,String line);
}
