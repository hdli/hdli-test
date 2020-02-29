package com.example.hdlitest.io.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * telnet localhost 9999
 * @author 李会东
 * @version 1.0
 * @date 2019-12-19 15:52
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {

        RequestHandle requestHandle = new RequestHandle();

        // 服务端监听一个端口，等待多个客户端的连接数据交互处理   这里只有一个main线程

        //代表服务端的Channle 就已经获得了
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //可以开启多个通道
        ServerSocketChannel serverChannel2 = ServerSocketChannel.open();



        //服务端的Channle 设置成非阻塞模式
        serverChannel.configureBlocking(false);
        serverChannel2.configureBlocking(false);
        //服务端Channle 监听在 9999端口上
        serverChannel.bind(new InetSocketAddress(9999));
        serverChannel2.bind(new InetSocketAddress(8888));
        System.out.println("NIO NIOServer has started,listening on port:"+serverChannel.getLocalAddress());

        //获取 Selector
        Selector selector = Selector.open();

        //Registers this channel with the given selector, returning a selection
        //     * key
        //将这个socket注册到selector上 并设置状态 是SelectionKey.OP_ACCEPT
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverChannel2.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //不断的循环监听当前的selector中的socket的状态
        while (true){
            //获取到当前
            int select = selector.select();
            if (select == 0){
                continue;
            }
            //* Registers this channel with the given selector, returning a selection
            //* key  channle注册到seletor中时返回一个selectionKey
            //selector中有多少个SelectionKey就有多少个channle
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //判断大航前key的状态，channle的状态
                if (key.isAcceptable()){
                    //服务端channle
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    //客户端的channle
                    SocketChannel clientChannel = channel.accept();
                    System.out.println("Connection from "+ clientChannel.getRemoteAddress());
                    clientChannel.configureBlocking(false);
                    //将当前的channle的状态改变 表示这个channle想要进行读写
                    clientChannel.register(selector,SelectionKey.OP_READ);
                }
                //channle进行数据的交互
                if (key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //读数据需要先经过buffer缓冲区   读数据
                    channel.read(buffer);
                    String request = new String(buffer.array()).trim();
                    buffer.clear();
                    System.out.println(String.format("From %s, %s",channel.getRemoteAddress(),request));
                    String response = requestHandle.handle(request);
                    //写数据也需要经过buffer缓冲区
                    channel.write(ByteBuffer.wrap(response.getBytes()));
                }
                iterator.remove();
            }
        }
    }

    static class RequestHandle{

        public String handle(String request){
            return  "From BIOServer Hello "+request+".\n";
        }
    }
}
