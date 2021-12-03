package com.example.hdlitest.tool;

import org.springframework.util.StopWatch;

/**
 * @author luyi
 * @date 2021/12/2 9:32 下午
 */
public class StopWatchTest {


    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println("aa="+stopWatch.getTotalTimeSeconds());
    }
}
