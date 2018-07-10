package com.soft.rpc.remote.service.impl;

import com.soft.rpc.remote.service.api.RemoteHelloService;

/**
 * 服务器端目标业务类，被代理对象
 */
public class RemoteHelloServiceImpl implements RemoteHelloService {
	
    @Override
    public String say(String name, int number) {
    	System.out.println(name + ":" + number);
        return name + ":" + number;
    }
    
}