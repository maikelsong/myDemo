/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: SingletonTest.java
 * Author:   raolesong
 * Date:     2018年7月12日 下午1:48:32
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.singleton;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年7月12日 下午1:48:32
 */
public class SingletonTest {
	
	private static class SingletonHolder{
		private static final SingletonTest INSTANCE = new SingletonTest();
	}

	private SingletonTest(){}
	
	public static SingletonTest getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	
}
