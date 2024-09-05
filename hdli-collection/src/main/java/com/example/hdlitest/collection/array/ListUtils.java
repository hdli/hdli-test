package com.example.hdlitest.collection.array;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/9/5 20:40
 */
public class ListUtils {


    public static void main(String[] args) {
        partition();
    }


    public static void partition(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            list.add(i);
        }
        /**
         * 将集合裁成多个集合，每个集合大小为10
         */
        List<List<Integer>> partition = Lists.partition(list, 10);
        for (List<Integer> integers : partition) {
            System.out.println(integers.size());
        }
    }
}
