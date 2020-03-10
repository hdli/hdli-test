package com.example.hdlitest.tool;

import java.io.File;
import java.net.URL;
import java.util.Objects;

/**
 *
 * 获取指定路径下的所有class类 实例
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 21:32
 */
public class DoScannerPathTest {

    /**
     * @param scanPackage  com.example.hdlitest
     */
    private void doScanner(String scanPackage){
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        assert url != null;
        File classDir = new File(url.getFile());
        for (File file : Objects.requireNonNull(classDir.listFiles())) {
            if (file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }else {
                if (!file.getName().endsWith(".class")){continue;}
                String className = scanPackage+"."+file.getName().replace(".class","");

            }
        }
    }

    /**
     * 将首字母变成小写的
     * @param simpleName
     * @return
     */
    private String toLowerFirstCase(String simpleName){
        //TODO 先判断simpleName的首字母是不是大写的
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
