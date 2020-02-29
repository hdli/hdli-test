package com.example.hdlitest.serializable;

public interface ISerializer {

    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte [] serialize(T obj);

    /**
     * 反序列化
     * @param date
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte [] date , Class<T> clazz);
}
