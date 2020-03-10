package com.example.hdlitest.designMode.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册式单例:又称为登记式单例，就是将每一个实例都登记到某一个地方，使用唯一的标 识获取实例。注册式单例有两种写法：一种为容器缓存，一种为枚举登记。
 * Spring中的做法，就是用这种注册式单例
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/28 17:16
 */
public class RegisterSingleton {

    private RegisterSingleton(){}

    //容器缓存
    private static final Map<String,Object> IOC = new ConcurrentHashMap<>();


    public static Object getInstance(String className){
        if (!IOC.containsKey(className)){
            synchronized (IOC){
                if (!IOC.containsKey(className)){
                    Object obj = null;
                    try {
                        obj = Class.forName(className).newInstance();
                        IOC.put(className,obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return obj;
                }
            }
        }
        return IOC.get(className);

    }
}
