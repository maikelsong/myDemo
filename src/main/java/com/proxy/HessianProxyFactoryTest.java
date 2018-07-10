/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: HessianProxyFactoryTest.java
 * Author:   raolesong
 * Date:     2018年7月10日 上午9:45:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.proxy;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年7月10日 上午9:45:51
 */
public class HessianProxyFactoryTest {
	
	public static void main(String[] args) {
		HessianProxyFactory proxyFactory = new HessianProxyFactory();
		try {
			IHello hello =  (IHello)proxyFactory.create(IHello.class, "");
			hello.sayHello("song");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
