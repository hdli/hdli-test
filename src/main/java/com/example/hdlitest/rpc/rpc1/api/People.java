package com.example.hdlitest.rpc.rpc1.api;

import java.io.Serializable;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 18:15
 */
public class People implements Serializable {

    private static final long serialVersionUID = -4006805620767040613L;
    private int a;

    private int b;

    public People() {
    }

    public People(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "People{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
