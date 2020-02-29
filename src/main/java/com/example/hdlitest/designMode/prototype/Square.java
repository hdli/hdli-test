package com.example.hdlitest.designMode.prototype;

import java.util.Scanner;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 15:29
 */
public class Square implements Shape {
    @Override
    public Object clone() {
        Square b = null;
        try {
            b = (Square) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("拷贝正方形失败!");
        }
        return b;
    }

    @Override
    public void countArea() {
        int a=0;
        System.out.print("这是一个正方形，请输入它的边长：");
        Scanner input=new Scanner(System.in);
        a=input.nextInt();
        System.out.println("该正方形的面积="+a*a+"\n");
    }
}
