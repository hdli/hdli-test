package com.example.hdlitest.designMode.decorator.battercake;

/**
 *
 * 建鸡蛋装饰者
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/5 17:55
 */
public class EggDecorator extends BattercakeDecorator {

    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }

    @Override
    void doSomething() {

    }
}
