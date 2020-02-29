package com.example.hdlitest.designMode.prototype;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 15:13
 */
public class PrototypeTest {

    /**
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
//        Realizetype obj1=new Realizetype();
//        Realizetype clone = obj1.clone();
//        System.out.println(obj1 == clone);


        ProtoTypeManager pm=new ProtoTypeManager();
        Shape obj1=(Circle)pm.getShape("Circle");
        obj1.countArea();
        Shape obj2=(Shape)pm.getShape("Square");
        obj2.countArea();
    }
}
