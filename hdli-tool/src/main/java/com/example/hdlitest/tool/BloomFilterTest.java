package com.example.hdlitest.tool;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @author luyi
 * @date 2024/3/2 16:23
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        // 创建布隆过滤器，预计插入的元素数量为1000，期望的误判率为0.01
        BloomFilter<String>  bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("UTF-8")),1000,0.01);

        // 向布隆过滤器中添加元素
        bloomFilter.put("hello");
        bloomFilter.put("world");

        System.out.println(bloomFilter.mightContain("hello"));
        System.out.println(bloomFilter.mightContain("guava"));

    }
}
