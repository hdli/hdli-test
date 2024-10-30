package com.example.hdlitest.leetcode.lianbiao;

import java.util.HashMap;
import java.util.Map;

/**
 *   LRU (最近最少使用) 缓存
 * @author luyi
 * @date 2024/10/29 21:19
 */
public class LRUCache_146_2 {


    class DLinkedNode{
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;

    private int size;

    private DLinkedNode head,tail;

    private final Map<Integer,DLinkedNode> cache = new HashMap<>();

    public LRUCache_146_2(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }


    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null){
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null){
            DLinkedNode newNode = new DLinkedNode(key,value);
            // 添加进哈希表
            cache.put(key,newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            size++;
            if (size > capacity){
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                size--;
            }
        }else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 删除链表尾节点（head 和 tail 是虚拟的 所以删除尾节点 删除tail的前一个节点）
     * @return
     */
    private DLinkedNode removeTail(){
        DLinkedNode res = tail.pre;
        removeNode(res);
        return res;
    }



    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addToHead(node);
    }

    /**
     * 双向联表 删除一个节点 断掉2条线
     * 当前节点的前节点指向当前节点的下一个节点
     * 当前节点的下一个节点指向当前节点的前节点
     * @param node
     */
    private void removeNode(DLinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * 添加头节点 （head 和 tail 是虚拟的 所以添加头节点就是添加 头节点的下一个）
     * 双向联表 添加一个节点 搭4条线
     * @param node
     */
    private void addToHead(DLinkedNode node){
        node.pre = head;
        node.next = head.next;
        head.next.pre=node;
        head.next = node;
    }

}
