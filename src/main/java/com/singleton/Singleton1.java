package com.singleton;
import java.lang.reflect.Constructor;

/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: Singleton1.java
 * Author:   raolesong
 * Date:     2016年2月17日 上午11:19:50
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author raolesong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Singleton1 {

	private static volatile Singleton1 instance;

	private Singleton1() {
	}

	public static Singleton1 getInstance() {
		if (instance == null) {
			synchronized (Singleton1.class) {
				if (instance == null) {
					instance = new Singleton1();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) throws Exception, SecurityException {
		Singleton1 in1 = Singleton1.getInstance();
		Singleton1 in2 = Singleton1.getInstance();
		System.out.println(in1 == in2);
		
		//反射破坏单例 --【如果单例是类实现】
		Constructor c = Singleton1.class.getDeclaredConstructor(null);
		Singleton1 in3 = (Singleton1)c.newInstance(null);
		Singleton1 in4 = (Singleton1)c.newInstance(null);
		System.out.println(in3 == in4);
		
		
		//反射破坏单例 --【如果单例是枚举实现】
		Constructor c2 = SingletonEnum.class.getDeclaredConstructor(null);
		SingletonEnum in5 = (SingletonEnum)c.newInstance(null);
		SingletonEnum in6 = (SingletonEnum)c.newInstance(null);
		System.out.println(in5 == in6);
				
				
		
	}

}
