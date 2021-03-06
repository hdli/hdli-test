package com.example.hdlitest.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/8 1:23
 */
public class BufferSlice {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        //改变子缓冲区的值

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b*=10;
            slice.put(i,b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining()>0){
            System.out.println(buffer.get());
        }
    }
}
