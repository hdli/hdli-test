package com.example.hdlitest.designMode.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 使用序列化 方式 判断是否会破坏单例
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/28 17:04
 */
public class SeriableSingletonTest {

    public static void main(String[] args) {
        LazyInnSingleton s1 = null;
        LazyInnSingleton s2 = LazyInnSingleton.getInstance();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (LazyInnSingleton)ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
