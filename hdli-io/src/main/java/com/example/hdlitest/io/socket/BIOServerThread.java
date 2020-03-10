package com.example.hdlitest.io.socket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 多线程 一个Socket 一个线程
 * @author 李会东
 * @version 1.0
 * @date 2019-12-19 15:47
 */
public class BIOServerThread {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try (ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BIOServer has started,listening on port:"+serverSocket.getLocalSocketAddress());
            //不断的等待客户端的连接
            while (true){
                System.out.println("开始去获取accept");
                Socket accept = serverSocket.accept();
                executorService.execute(new ClientHandle(accept));
                System.out.println("Connection from "+accept.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandle implements Runnable{
        private Socket accept;

        public ClientHandle(Socket accept) {
            this.accept = accept;
        }

        @Override
        public void run() {
            try (Scanner scanner = new Scanner(accept.getInputStream())){
                //针对客户端能够不断的数据读写
                while (true){
                    String request = scanner.nextLine();// Client ---> 数据 阻塞
                    if ("quit".equals(request)){
                        break;
                    }
                    System.out.println(String.format("From %s : %s",accept.getRemoteSocketAddress(),request));
                    String response = "From BIOServer Hello "+request+".\n";
                    accept.getOutputStream().write(response.getBytes());
                }
            } catch (IOException e) {
                System.out.println("Caught exception: "+e);
                throw new RuntimeException(e);
            }
        }
    }
}
