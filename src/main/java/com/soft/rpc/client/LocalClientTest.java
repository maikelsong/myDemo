package com.soft.rpc.client;
import java.lang.reflect.InvocationHandler;

import com.soft.rpc.remote.service.api.RemoteHelloService;


public class LocalClientTest {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        Integer port = 8001;
        Class<?> classType = com.soft.rpc.remote.service.api.RemoteHelloService.class;
        InvocationHandler h = new ServiceInvocationHandler(classType, host, port);
        RemoteHelloService serviceProxy = (RemoteHelloService) RemoteServiceProxyFactory.getRemoteServiceProxy(h);
        //实际是调用ServiceInvocationHandler.invoke方法
        String result = serviceProxy.say("SunnyMarkLiu", 22); 
        System.out.println("调用远程方法say的结果：" + result);
    }
}