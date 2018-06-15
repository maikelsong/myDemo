/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: DynamicProxy.java
 * Author:   raolesong
 * Date:     2018年1月22日 下午2:31:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月22日 下午2:31:26
 */
public class DynamicProxy implements InvocationHandler {
	
	//原始对象
	private Object originalObj;
	
	public Object bindObj(Object obj){
		this.originalObj = obj;
		return Proxy.newProxyInstance(this.originalObj.getClass().getClassLoader(), this.originalObj.getClass().getInterfaces(), this);
	}
	

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("执行方法之前加的业务");
		Object obj = method.invoke(originalObj, args);
		System.out.println("执行方法之后加的业务...");
		return obj;
	}
	
	
	public static void main(String[] args) {
		/* 设置此系统属性,让JVM生成的Proxy类写入文件.保存路径为：com/sun/proxy(如果不存在请手工创建) */  
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); 
		DynamicProxy dynamicProxy = new DynamicProxy();
		
		Class<?> cls = Proxy.getProxyClass(IHello.class.getClassLoader(), IHello.class.getInterfaces());
		System.out.println(cls);
		
		IHello hello = (IHello)dynamicProxy.bindObj(new Hello());
		hello.sayHello();
//		hello.toString();
		
	}

}
