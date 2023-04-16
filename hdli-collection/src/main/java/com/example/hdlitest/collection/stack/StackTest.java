package com.example.hdlitest.collection.stack;

/**
 * @author luyi
 * @date 2023/4/16 4:01 PM
 */
public class StackTest {


    /**
     * 轻松掌握栈的基本操作
     * 栈的所有操作都是基于栈顶进行的，这是它的特色
     * https://blog.csdn.net/m0_52517879/article/details/123795249
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * 使用数据实现一个栈（暂时不考虑扩容）
     */
    static class ArrayStack{

        String [] data;
        /**
         * 栈的大小
         */
        int size;
        /**
         * 栈顶的位置
         * 当栈为空时 -1
         */
        int top = -1;


        public ArrayStack() {
            this.size = 20;
            this.data = new String[this.size];
        }

        public ArrayStack(int size) {
            this.size = size;
            this.data = new String[this.size];
        }

        /**
         * 添加元素
         * @param value
         * @return
         */
        public boolean push(String value){
            //数组越界
            if(this.top >= this.size -1){
                return false;
            }
            this.top++;
            this.data[this.top] = value;
            return true;
        }

        /**
         * 弹出栈顶
         * @return
         */
        public String pop(){
            if (this.top == -1){
                return null;
            }
            String value = this.data[this.top];
            this.top--;
            return value;
        }


        public String peek(){
            if (this.top == -1){
                return null;
            }
            return this.data[this.top];
        }


    }

    /**
     * 使用链表实现一个栈
     * 1、链表不需要声明长度空间，链表本身具备无限扩容的能力
     * 2、链表应选用链表头作为栈顶元素的选择。因为链表头操作的时间复杂度为 O(1)，链表尾操作的时间复杂度为 O(N)，所以将链表头作为栈顶是最佳选择
     */
    static class LinkedStack{
        Node header;

        public LinkedStack() {
        }

        public boolean push(String value){
            if (header == null){
                this.header = new Node(value);
            }else {
                this.header = new Node(value,header);
            }
            return true;
        }

        public String pop(){
            if (this.header == null){
                return null;
            }
            Node node = this.header;
            this.header = node.getNext();
            node.setNext(null);
            return node.getContent();
        }


        public String peek(){
            if (this.header == null){
                return null;
            }
            return this.header.getContent();
        }

    }

    static class Node{

        String content;

        Node next;

        public Node(String content) {
            this.content = content;
        }

        public Node(String content,Node node){
            this.content = content;
            this.next = node;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getContent() {
            return content;
        }

        public Node getNext() {
            return next;
        }
    }


}

