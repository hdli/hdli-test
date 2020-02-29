package com.example.hdlitest.designMode.builder;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 16:36
 */
public class BuilderTest {

    public static void main(String[] args) {
        // Builder 模式
        NewComputer newComputer = new NewComputer.Builder()
        .cpu("cpu")
        .screen("screen")
        .memory("memory")
        .mainboard("mainboard")
        .build();
    }
}
