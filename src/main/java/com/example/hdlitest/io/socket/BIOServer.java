package com.example.hdlitest.io.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *  telnet localhost 8888
 *  与服务端经行交互测试
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 22:19
 */
public class BIOServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BIOServer has started,listening on port:"+serverSocket.getLocalSocketAddress());
            //不断的等待客户端的连接
            while (true){
                System.out.println("开始去获取accept");
                //accept 方法阻塞了 ---> 等待客户端连接
                Socket accept = serverSocket.accept();
                System.out.println("Connection from "+accept.getRemoteSocketAddress());
                try (Scanner scanner = new Scanner(accept.getInputStream())){
                    //针对客户端能够不断的数据读写
                    while (true){
                        // Client ---> 数据 阻塞
                        String request = scanner.nextLine();
                        if ("quit".equals(request)){
                            break;
                        }
                        System.out.println(String.format("From %s : %s",accept.getRemoteSocketAddress(),request));
                        String response = "From BIOServer Hello "+request+".\n";
                        accept.getOutputStream().write(response.getBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
