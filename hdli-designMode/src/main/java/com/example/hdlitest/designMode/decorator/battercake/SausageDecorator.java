package com.example.hdlitest.designMode.decorator.battercake;

/**
 *
 * 香肠装饰者
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/5 17:57
 */
public class SausageDecorator extends BattercakeDecorator {
    public SausageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }

    @Override
    void doSomething() {

    }
}
