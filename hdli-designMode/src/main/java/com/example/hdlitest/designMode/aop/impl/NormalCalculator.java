package com.example.hdlitest.designMode.aop.impl;


import com.example.hdlitest.designMode.aop.Calculator;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-2 10:50
 */
public class NormalCalculator implements Calculator<Integer> {

    public String pubField;

    private String remak;

    public NormalCalculator() {
    }

    public NormalCalculator(String remak) {
        this.remak = remak;
    }

    public String getRemak() {
        return remak;
    }

    public void setRemak(String remak) {
        this.remak = remak;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        return a+b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a-b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a*b;
    }
}
