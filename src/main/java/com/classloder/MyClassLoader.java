/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: MyClassLoader.java
 * Author:   raolesong
 * Date:     2018年1月16日 下午4:49:41
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.classloder;

/**
 * 类加载器： 双亲委托模式,参考父类：ClassLoader.loadClass(String name, boolean resolve)方法
 * 自定义子类加载器需要重写父类的findClass方法
 * @author raolesong
 * 2018年1月16日 下午4:49:41
 */
public class MyClassLoader extends ClassLoader{

	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}
	
}
