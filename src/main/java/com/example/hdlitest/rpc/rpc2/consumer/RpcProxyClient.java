package com.example.hdlitest.rpc.rpc2.consumer;

import com.example.hdlitest.rpc.rpc2.api.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-22 17:10
 */
public class RpcProxyClient {

    public static <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {
        if (interfaceCls == null) {
            throw new IllegalArgumentException("调用服务为空");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("主机不能为null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("端口不合法" + port);
        }
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }

    static class RemoteInvocationHandler implements InvocationHandler {
        private String host;
        private int port;

        public RemoteInvocationHandler(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //请求会进入到这里
            System.out.println("come in");
            //请求数据的包装
            RpcRequest rpcRequest=new RpcRequest();
            rpcRequest.setClassName(method.getDeclaringClass().getName());
            rpcRequest.setMethodName(method.getName());
            rpcRequest.setParameters(args);
            rpcRequest.setVersion("v1.0");
            //远程通信
            RpcNetTransport netTransport=new RpcNetTransport(host,port);

            return netTransport.send(rpcRequest);
        }
    }
}
