package com.example.hdlitest.rpc.rpc2.provider;

import com.example.hdlitest.rpc.rpc2.api.RpcRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-22 17:00
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Map<String,Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try {
            objectInputStream=new ObjectInputStream(socket.getInputStream());

            //输入流中应该有什么东西？
            //请求哪个类，方法名称、参数
            RpcRequest rpcRequest=(RpcRequest)objectInputStream.readObject();
            //反射调用本地服务
            Object result=invoke(rpcRequest);

            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //反射调用
    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String serviceName=request.getClassName();
        String version=request.getVersion();
        //增加版本号的判断
        if(!StringUtils.isEmpty(version)){
            serviceName+="-"+version;
        }
        Object service=handlerMap.get(serviceName);
        if (service == null){
            throw new RuntimeException("service not found:"+serviceName);
        }

        //拿到客户端请求的参数
        Object[] args=request.getParameters();
        Method method=null;
        //根据请求的类进行加载
        Class clazz=Class.forName(request.getClassName());
        if (args != null){
            //获得每个参数的类型
            Class<?>[] types = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            //sayHello, saveUser找到这个类中的方法
            method=clazz.getMethod(request.getMethodName(),types);
        }else {
            //sayHello, saveUser找到这个类中的方法
            method=clazz.getMethod(request.getMethodName());
        }
        //HelloServiceImpl 进行反射调用
        return method.invoke(service,args);
    }
}
