package com.example.hdlitest.tool;

import java.io.*;

/**
 *
 * 将指定路径下的文件信息合并到指定文件中
 * @author 李会东
 * @version 1.0
 * @date 2019-10-21 17:41
 */
public class TestDirErgodic {
    private static int depth=1;

    public static void find(File dirFile,File outFlie) throws IOException {
        //判断该文件或目录是否存在，不存在时在控制台输出提醒
        if (!dirFile.exists()) {
            System.out.println("do not exit");
            return;
        }
        //判断如果不是一个目录，就判断是不是一个文件，是文件则输出文件路径
        if (!dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                copyFileUsingFileStreams(dirFile,outFlie);
            }
        }
        File[] listFiles = dirFile.listFiles();
        assert listFiles != null;
        for (File f : listFiles) {
            if (f.isFile()){
                if (!f.getName().startsWith("Test")){
                    copyFileUsingFileStreams(f,outFlie);
                }
            }
            if (f.isDirectory()){
                if(!f.getName().contains("test")){
                    find(f,outFlie);
                }
            }
        }

    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest,true);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("开始-----------------------------------------");
        long start = System.currentTimeMillis();
        File soure = new File("E:\\company\\credit2go\\project_new\\02_project\\cdcrb-fitfiness\\fitness-operation\\src\\main\\java");
        File outFlie = new File("E:\\test\\pc.txt");
        find(soure,outFlie);
        System.out.println("结束-------------用时："+(System.currentTimeMillis()-start));
    }
}
