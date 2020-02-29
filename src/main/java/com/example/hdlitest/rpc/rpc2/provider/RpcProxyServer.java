package com.example.hdlitest.rpc.rpc2.provider;

/**
 *
 * 发布服务看 RpcServerSpring
 * @author 李会东
 * @version 1.0
 * @date 2020-1-22 15:40
 */
public class RpcProxyServer {

//    private static ExecutorService executorService= Executors.newCachedThreadPool();
//
//    public static void publisher(Object service,int port){
//        ServerSocket serverSocket=null;
//        try {
//            serverSocket=new ServerSocket(port);
//            while(true) {//不断接受请求
//                Socket socket=serverSocket.accept();//BIO
//                //每一个socket 交给一个processorHandler来处理
//                executorService.execute(new ProcessorHandler(socket,service));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(serverSocket!=null){
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
