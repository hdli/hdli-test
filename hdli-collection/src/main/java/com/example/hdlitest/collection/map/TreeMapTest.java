package com.example.hdlitest.collection.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-9 15:08
 */
public class TreeMapTest {

    public static void main(String[] args) {
        /**
         * TreeMap本身是从小到大的顺序排列的
         */
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(2,"a");
        map.put(1,"a");
        map.put(4,"a");
        map.put(3,"a");
        map.put(5,"a");

        for (Map.Entry<Integer, String> e :
                map.entrySet()) {
            System.out.println(e.getKey());
        }
        System.out.println("-----------------------------------------");
        /**
         * descendingMap() 会使TreeMap变成降序
         */
        Map<Integer, String> map1 = map.descendingMap();

        for (Map.Entry<Integer, String> e :
                map1.entrySet()) {
            System.out.println(e.getKey());
        }
    }
}
