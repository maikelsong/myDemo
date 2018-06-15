package com.apiframe.core;

/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: ApiHander.java
 * Author:   raolesong
 * Date:     2018年1月5日 下午5:27:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月5日 下午5:27:30
 */
public class ApiHander {
	
	
	public void doRealMethod(String methodName,Object...args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clazz = ApiStore.apiMap.get(methodName);
		Method method = clazz.getMethod(methodName, clazz);
		method.invoke(clazz, args);
	}

}
