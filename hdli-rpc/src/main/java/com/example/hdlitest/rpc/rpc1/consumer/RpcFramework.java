package com.example.hdlitest.rpc.rpc1.consumer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 18:16
 */
public class RpcFramework {


    public static <T> T call(Class interfaceClass, String host, int port){
        if(interfaceClass==null){
            throw new IllegalArgumentException("调用服务为空");
        }
        if(host==null||host.length()==0){
            throw new IllegalArgumentException("主机不能为null");
        }
        if(port<=0||port>65535){
            throw new IllegalArgumentException("端口不合法"+port);
        }
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[]{interfaceClass},new CallerHandler(host,port));
    }

    static class CallerHandler implements InvocationHandler {
        private String host;
        private int port;

        public CallerHandler(String host, int port) {
            this.host = host;
            this.port = port;
//            SERVER = new InetSocketAddress(host, port);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            Socket socket = new Socket(host, port);
            try {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                try {
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(arguments);
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    try {
                        Object result = input.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }
                        return result;
                    }finally {
                        input.close();
                    }
                }finally {
                    output.close();
                }
            } finally {
                socket.close();
            }
//            return null;
        }
    }

}
