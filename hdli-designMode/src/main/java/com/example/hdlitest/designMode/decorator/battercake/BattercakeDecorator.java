package com.example.hdlitest.designMode.decorator.battercake;

/**
 *
 * 扩展套餐的抽象装饰者
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/5 17:53
 */
public abstract class BattercakeDecorator extends Battercake {
    /**
     * 静态代理，委派
     */
    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }

    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }

    /**
     * 扩展方法
     */
    abstract void doSomething();
}
