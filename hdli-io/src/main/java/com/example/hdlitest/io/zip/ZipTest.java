package com.example.hdlitest.io.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author luyi
 * @date 2023/4/26 10:33 AM
 */
public class ZipTest {


    public static void main(String[] args) {
        // 文件目录
        File sourceDir = new File("/data");

        // 压缩文件
        File zipFile = new File("/data.zip");

        try {
            // 创建ZipOutputStream对象
            OutputStream os = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(os);

            // 递归遍历文件目录并添加到压缩文件
            addFilesToZip(sourceDir, sourceDir.getName(), zos);

            // 关闭ZipOutputStream
            zos.close();

            System.out.println("压缩完成");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 递归遍历文件目录并添加到压缩文件
    private static void addFilesToZip(File sourceFile, String parentName, ZipOutputStream zos) throws IOException {
        // 如果是目录，则递归遍历子文件
        if (sourceFile.isDirectory()) {
            for (File file : sourceFile.listFiles()) {
                addFilesToZip(file, parentName + "/" + file.getName(), zos);
            }
        } else { // 如果是文件，则添加到压缩文件
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(sourceFile);
            zos.putNextEntry(new ZipEntry(parentName));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            fis.close();
        }
    }
}
