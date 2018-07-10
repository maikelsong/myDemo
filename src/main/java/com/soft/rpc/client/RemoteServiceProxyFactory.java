package com.soft.rpc.client;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态创建RemoteService代理类的工厂
 */
public class RemoteServiceProxyFactory {

    public static Object getRemoteServiceProxy(InvocationHandler h) {
        Class<?> classType = ((ServiceInvocationHandler) h).getClassType();
        // 获取动态代理类
        Object proxy = Proxy.newProxyInstance(classType.getClassLoader(), 
                new Class[]{classType}, h);
        return proxy;
    }
}