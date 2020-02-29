package com.example.hdlitest.test;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 15:35
 */
public class TestMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        TestA a = new TestA();
        a.add();

        TestA clone = (TestA) a.clone();
        clone.add();
    }
}
