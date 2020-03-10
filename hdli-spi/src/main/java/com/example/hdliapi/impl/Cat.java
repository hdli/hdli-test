package com.example.hdliapi.impl;

import com.example.hdliapi.IShout;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/9 17:52
 */
public class Cat implements IShout {
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}
