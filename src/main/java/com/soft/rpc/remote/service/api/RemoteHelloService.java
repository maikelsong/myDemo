package com.soft.rpc.remote.service.api;
/**
 * Service接口。代理类和被代理类抖需要实现该接口
 */
public interface RemoteHelloService {
	
    public String say(String name, int number);
    
}