package com.example.hdlitest.servicetest;

import com.alibaba.fastjson.JSONObject;

/**
 * @author luyi
 * @date 2025/1/13 19:07
 */
public class Test222 {


    public static void main(String[] args) {
        JSONObject a = new JSONObject();
        Integer a1 = a.getInteger("a");
        System.out.println(a1);
    }
}
