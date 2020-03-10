package com.example.hdlitest.collection.map;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-8-20 22:27
 */
public class MapTest {

    public static void main(String[] args) {

        String at = "内存,颜色";
        List<String> list = Arrays.asList(at.split(","));

        Map<String, Set<String>> result = new HashMap<>();
        for (String a : list) {
            result.put(a,new LinkedHashSet<>());
        }

        String atList = "{\"内存\":\"2G\",\"颜色\":\"红色\"}";
        String atList2 = "{\"内存\":\"4G\",\"颜色\":\"蓝色\"}";
        String atList3 = "{\"内存\":\"8G\",\"颜色\":\"蓝色\"}";
        List<String> req = new ArrayList<>();
        req.add(atList2);
        req.add(atList3);
        req.add(atList);

        for (int i = 0;i<req.size();i++) {
            Map<String,String> map = JSON.parseObject(req.get(i), HashMap.class);

            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                Set<String> str = result.get(key);
                str.add(map.get(key));
                if (i == (req.size()-1) && !iterator.hasNext()){
                    System.out.println("最后排序");
                    str = str.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toCollection(LinkedHashSet::new));
                    result.put(key,str);
                }
            }
//            for (Map.Entry<String,String> entry : map.entrySet()) {
//                Set<String> str = result.get(entry.getKey());
//                str.add(entry.getValue());
//                str = str.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toCollection(LinkedHashSet::new));
//                result.put(entry.getKey(),str);
//            }
        }

        System.out.println(JSON.toJSONString(result));
    }

}

