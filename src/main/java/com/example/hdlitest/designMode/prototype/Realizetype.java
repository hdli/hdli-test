package com.example.hdlitest.designMode.prototype;

/**
 * 具体原型类
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 15:12
 */
public class Realizetype implements Cloneable {

    public Realizetype() {
        System.out.println("具体原型创建成功");
    }

    @Override
    protected Realizetype clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return (Realizetype) super.clone();
    }
}
