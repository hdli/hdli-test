package com.example.hdlitest.rpc.rpc1.provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 18:35
 */
public class RpcFramework {

    public static void publish(final Object service,int port) throws IOException {
        if(service==null){
            throw new IllegalArgumentException("发布服务不能是空");
        }
        if(port<=0 || port >65535){
            throw new IllegalArgumentException("端口不合法"+port);
        }
        ServerSocket server=new ServerSocket(port);
        while (true) {
            try{
                final Socket socket=server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                                try {
                                    String methodName = input.readUTF();
                                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                                    Object[] arguments = (Object[]) input.readObject();
                                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                    try {
                                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                                        Object result = method.invoke(service, arguments);
                                        output.writeObject(result);
                                    } catch (Throwable t) {
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                    }
                                } finally {
                                    input.close();
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
