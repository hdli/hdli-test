package com.example.hdlitest.designMode.prototype;

public interface Shape extends Cloneable {
    /**
     * 拷贝
     * @return
     */
    Object clone();

    /**
     * 计算面积
     */
    void countArea();
}
