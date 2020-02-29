package com.example.hdlitest.serializable;

import java.io.Serializable;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-20 22:43
 */
public class User implements Serializable {

    private static final long serialVersionUID = 7312068223694042134L;

    private String name;

    private int age;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
