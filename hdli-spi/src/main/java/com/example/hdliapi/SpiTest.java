package com.example.hdliapi;

import java.util.ServiceLoader;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/9 17:54
 */
public class SpiTest {

    //该调用可以在主类中使用
    public static void main(String[] args) {
        /**
         *
         * 1 应用程序调用ServiceLoader.load方法
         * ServiceLoader.load方法内先创建一个新的ServiceLoader，并实例化该类中的成员变量，包括：
         *      loader(ClassLoader类型，类加载器)
         *      acc(AccessControlContext类型，访问控制器)
         *      providers(LinkedHashMap<String,S>类型，用于缓存加载成功的类)
         *      lookupIterator(实现迭代器功能)
         * 2 应用程序通过迭代器接口获取对象实例
         *  ServiceLoader先判断成员变量providers对象中(LinkedHashMap<String,S>类型)是否有缓存实例对象，如果有缓存，直接返回。
         *  如果没有缓存，执行类的装载，实现如下：
         *  (1) 读取META-INF/services/下的配置文件，获得所有能被实例化的类的名称，值得注意的是，
         *  ServiceLoader可以跨越jar包获取META-INF下的配置文件
         *  (2) 通过反射方法Class.forName()加载类对象，并用instance()方法将类实例化。
         *  (3) 把实例化后的类缓存到providers对象中，(LinkedHashMap<String,S>类型）然后返回实例对象。
         *
         *
         */
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);

        shouts.forEach(IShout::shout);
    }
}
