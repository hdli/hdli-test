package com.example.hdlitest.serializable.impl;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.example.hdlitest.serializable.ISerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-20 22:45
 */
public class HessianSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        HessianOutput hessianOutput=new HessianOutput(outputStream);
        try {
            hessianOutput.writeObject(obj);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream inputStream=new ByteArrayInputStream(data);
        HessianInput hessianInput=new HessianInput(inputStream);
        try {
            return (T)hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
