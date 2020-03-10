package com.example.hdlitest.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-9 19:43
 */
public class Lihuidong {

    //存放 异常类型 出现的次数
    Map<String, Integer> repeatMap = new ConcurrentHashMap<>();

    public void analysis(String path) throws IOException {
        readFile(path, new HandleLine() {
            @Override
            public void handle(String line) {
                //简单判断这行是否发生异常
                if (!line.toLowerCase().contains("exception")){
                    return;
                }
                String[] strings = line.split("|");
                String errorType = strings[0];
                if (repeatMap.containsKey(errorType)){
                    repeatMap.put(errorType,repeatMap.get(errorType)+1);
                }else {
                    repeatMap.put(errorType,1);
                }
            }
        });
    }



    public int getErrorCount(String errorType) throws IOException {
        Files.lines(Paths.get(new File("e://test.fa").getPath())).count();
        return repeatMap.get(errorType);
    }

    public static void readFile(String path, HandleLine handleLine) throws IOException {
        try (FileReader in = new FileReader(path); BufferedReader br = new BufferedReader(in)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                handleLine.handle(line);
            }
        }
    }

}

interface HandleLine{

    void handle(String line);
}
