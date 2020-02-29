package com.example.hdlitest.designMode.singleton;

/**
 * 利用枚举实现单例模式
 * @author 李会东
 * @version 1.0
 * @date 2019-12-7 22:32
 */
public enum EnumSingleton {
    /**
     * 首先，在枚举中我们明确了构造方法限制为私有，在我们访问枚举实例时会执行构造方法，同时每个枚举实例都是static final类型的，也就表明只能被实例化一次。在调用构造方法时，我们的单例被实例化。
     * 也就是说，因为enum中的实例被保证只会被实例化一次，所以我们的INSTANCE也被保证实例化一次
     *
     * 单元素的枚举类型已经成为实现Singleton的最佳方法
     * https://www.cnblogs.com/kaleidoscope/p/9636779.html
     */
    INSTANCE;

    private SingletonClassTest singletonClass;

    EnumSingleton() {
        System.out.println(Thread.currentThread().getName()+"构造方法只被执行一次");
        singletonClass = new SingletonClassTest();
    }

    public SingletonClassTest getInstance(){
        System.out.println(Thread.currentThread().getName()+"获取实例");
        return singletonClass;
    }

}
