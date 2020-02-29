package com.example.hdlitest.test;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 15:34
 */
public class TestA implements Cloneable {

    private static int a;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void add(){
        System.out.println(++a);
    }

    public static void main(String[] args) {
        Integer a = new Integer(10);
        float b = 80.1f;
        String c = "aaa";
        TestA testA = new TestA();
        System.out.println("Before : a="+a+" b="+b +"c="+c);
        test(testA,a,b,c);
        System.out.println("After : a="+a+" b="+b+"c="+c);
    }

    public static void test(TestA testA,Integer a,float b,String c){
        a = new Integer(30);
        b = 89.1f;
        c = "bbbb";
    }
}
