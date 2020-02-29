package com.example.hdlitest.designMode.strategy.factory;

import com.example.hdlitest.config.ContextHolder;
import com.example.hdlitest.designMode.strategy.StrategyInterface;
import com.example.hdlitest.designMode.strategy.annotation.Pay;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-6 0:03
 */
public class StrategyFactory {

    private static StrategyFactory strategyFactory = new StrategyFactory();

    private StrategyFactory(){}

    public static StrategyFactory getInstance(){
        return strategyFactory;
    }

    private static HashMap<Integer,String> source_map = new HashMap<>();

    static {
        /**
         * 需要依赖reflections包
         * <dependency>
         *     <groupId>org.reflections</groupId>
         *     <artifactId>reflections</artifactId>
         *     <version>0.9.11</version>
         * </dependency>
         *
         * 也可以通过 Spring上下文获取有某个注解的所有实现
         */
        Reflections reflections = new Reflections("com.example.hdlitest.designMode.strategy.impl");
        for (Class<?> aClass : reflections.getTypesAnnotatedWith(Pay.class)) {
            Pay pay = aClass.getAnnotation(Pay.class);
            source_map.put(pay.channleId(),aClass.getCanonicalName());
        }
    }

    public Map<Integer,String> getSourceMap(){
        return source_map;
    }

    public StrategyInterface creat(int channelId){
        String clazz = source_map.get(channelId);
        StrategyInterface instance = null;
        try {
            instance = (StrategyInterface) ContextHolder.getBean(Class.forName(clazz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return instance;
        }
        return instance;
    }

    public static void main(String[] args) {
        Map<Integer,String> map = StrategyFactory.getInstance().getSourceMap();
        System.out.println(map);
    }
}
