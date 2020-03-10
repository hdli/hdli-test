package com.example.hdlitest.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 按行数切分大文件的demo
 * @author 李会东
 * @version 1.0
 * @date 2020-1-14 17:04
 */
public class CutFile {


    public static void main(String[] args) throws IOException {
//        File file = new File("E:\\test\\pc.txt");
        cat("E:\\test\\pc.txt",3);
    }


    /**
     *
     * @param filePath 文件路径
     * @param splitNum     要切成多少分
     */
    public static void cat(String filePath,int splitNum) throws IOException{
        long timer = System.currentTimeMillis();
        //设读取文件的缓存为20MB
        int bufferSize = 20 * 1024 * 1024;

        //建立缓冲文本输入流
        File file = new File(filePath);
        String parent = file.getParent();
        String name = file.getName();
        String substring = name;
        if (name.contains(".")){
            substring = name.substring(0, name.lastIndexOf("."));
        }
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(in,bufferSize)) {
            //获取文件行数
            long fileLines = Files.lines(Paths.get(filePath)).count();
            //每个块的行数
            long perSplitLines = fileLines / splitNum;
            for (int i = 1; i <= splitNum; ++i){
                //分割
                //每个块建立一个输出
                FileWriter output = new FileWriter(parent+File.separator+name.replace(substring,substring+i));
                String line = null;
                //逐行读取，逐行输出
                for (long lineCounter = 0; (line = getLine(lineCounter,perSplitLines,i,splitNum,br)) != null; ++lineCounter){
                    //'\r'是linux上的，windows的java换行符是'\r\n'
                    output.append(line + "\r");
                }
                output.flush();
                output.close();
                output = null;
            }
        }
        timer = System.currentTimeMillis() - timer;
        System.out.println("处理时间：" + timer);
    }

    private static String getLine(long lineCounter, long perSplitLines, int i, int splitNum, BufferedReader br) throws IOException {
        String line = null;
        //如果是最后一快，但文件还有剩余就放到最后一个文件中
        if (i == splitNum && (line = br.readLine()) != null){
            return line;
        }
        if (lineCounter < perSplitLines && (line = br.readLine()) != null){
            return line;
        }
        return line;
    }


    /**
     * 高效获取大文件的行数
     * @param filePath
     * @return
     */
    public static int getFileLineNum(String filePath) {
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))){
            long skip = lineNumberReader.skip(Long.MAX_VALUE);
            int lineNumber = lineNumberReader.getLineNumber();
            //实际上是读取换行符数量 , 所以需要+1
            return lineNumber + 1;
        } catch (IOException e) {
            return -1;
        }
    }

    /**
     * Java8新的工具方法
     * 实际上 , Java8的新方法时间上并没有LineNumberReader快
     * @param filePath
     * @return
     */
    public static long getFileLineNumJDK8(String filePath) {
        try {
            return Files.lines(Paths.get(filePath)).count();
        } catch (IOException e) {
            return -1;
        }
    }
}
