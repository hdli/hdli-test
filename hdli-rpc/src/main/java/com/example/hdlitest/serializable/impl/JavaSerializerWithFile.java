package com.example.hdlitest.serializable.impl;

import com.example.hdlitest.serializable.ISerializer;

import java.io.*;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-21 22:41
 */
public class JavaSerializerWithFile implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("user")));

            outputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] date, Class<T> clazz) {
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream
                            (new File("user")));
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
