package com.example.hdlitest.designMode.singleton;

import java.io.Serializable;

/**
 *
 * 静态内部类 实现单例
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/27 23:55
 */
public class LazyInnSingleton implements Serializable {

    //默认使用LazyInnSingleton的时候，会先初始化内部类
    //如果没使用的话，内部类是不加载的
    private LazyInnSingleton(){
        //应对反射获取构造创建实例
        if (LazyHolder.LAZY != null){
            throw new RuntimeException("不允许构建多个实例");
        }
    }

    /**
     * 每一个关键字都不是多余的
     * static 是为了使单例的空间共享
     * final 保证这个方法不会被重写，重载
     * @return
     */
    public static final LazyInnSingleton getInstance(){
        //在返回结果以前，一定会先加载内部类
        return LazyHolder.LAZY;
    }

    private static class LazyHolder{
        private static final LazyInnSingleton LAZY = new LazyInnSingleton();
    }


    /**
     * 以上方法还是不能解决 序列化 破坏 单例 但
     * 只需要增加 readResolve()方法即可，
     *  看 JDK 的 源 码 实 现 以 一 清 二 楚 了 。 我 们 进 入 ObjectInputStream 类的 readObject()方法
     * @return
     */
    private Object readResolve(){
        return LazyHolder.LAZY;
    }
}
