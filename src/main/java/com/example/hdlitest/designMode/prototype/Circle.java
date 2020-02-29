package com.example.hdlitest.designMode.prototype;

import java.util.Scanner;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 15:27
 */
public class Circle implements Shape {
    @Override
    public Object clone() {
        Circle w=null;
        try {
            w = (Circle) super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("拷贝圆失败!");
        }
        return w;
    }

    @Override
    public void countArea() {
        int r=0;
        System.out.print("这是一个圆，请输入圆的半径：");
        Scanner input=new Scanner(System.in);
        r=input.nextInt();
        System.out.println("该圆的面积="+3.1415*r*r+"\n");
    }
}
