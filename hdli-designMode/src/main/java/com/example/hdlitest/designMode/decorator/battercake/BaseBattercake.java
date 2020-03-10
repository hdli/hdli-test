package com.example.hdlitest.designMode.decorator.battercake;

/**
 *
 * 基础套餐 煎饼
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/5 17:51
 */
public class BaseBattercake extends Battercake {

    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
