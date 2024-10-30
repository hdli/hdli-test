package com.example.hdlitest.leetcode.lianbiao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luyi
 * @date 2024/10/29 21:05
 */
public class LRUCache_146 extends LinkedHashMap {

    private int capacity;

    public LRUCache_146(int capacity) {
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return (int) super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }
}
