package com.example.hdlitest.test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-10 20:36
 */
public class ThreadInfo {

    private int start;
    private int end;
    private int finished;

    public ThreadInfo(int start, int end, int finished) {
        this.start = start;
        this.end = end;
        this.finished = finished;
    }

}
