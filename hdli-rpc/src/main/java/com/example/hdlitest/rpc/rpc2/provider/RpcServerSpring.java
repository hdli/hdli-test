package com.example.hdlitest.rpc.rpc2.provider;

import com.example.hdlitest.rpc.rpc2.provider.annontation.RpcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 将发布服务，交给Spring
 *
 * 需要确保afterPropertiesSet与 setApplicationContext 的执行顺序
 *
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/8 0:15
 */
//@Component
public class RpcServerSpring implements ApplicationContextAware, InitializingBean {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private Map<String, Object> handlerMap = new HashMap();

    private int port;

    public RpcServerSpring(int port) {
        this.port = port;
    }

    /**
     * 初始化
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            //不断接受请求
            while (true) {
                //BIO
                Socket socket = serverSocket.accept();
                //每一个socket 交给一个processorHandler来处理
                executorService.execute(new ProcessorHandler(socket, handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过applicationContext上下文获取RpcService注解的服务
     *
     * @param applicationContext spring上下文
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (serviceBeanMap.isEmpty()) {
            return;
        }
        for (Object servcieBean : serviceBeanMap.values()) {
            //拿到注解
            RpcService rpcService=servcieBean.getClass().getAnnotation((RpcService.class));
            //拿到接口类定义
            String serviceName=rpcService.value().getName();
            //拿到版本号
            String version=rpcService.version();
            if(!StringUtils.isEmpty(version)){
                serviceName+="-"+version;
            }
            handlerMap.put(serviceName,servcieBean);
        }
    }
}
