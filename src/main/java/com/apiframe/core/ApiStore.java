/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: ApiStore.java
 * Author:   raolesong
 * Date:     2018年1月5日 下午2:38:45
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.apiframe.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月5日 下午2:38:45
 */
@Component
public class ApiStore implements InitializingBean,ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	public static Map<String,Class> apiMap = new HashMap<String,Class>(); 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Service.class);
		Iterator<String> iterator = beanMap.keySet().iterator() ; 
		while(iterator.hasNext()){
			Class clazz = beanMap.get(iterator.next()).getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods){
				if(method.isAnnotationPresent(ApiMapping.class)){
					apiMap.put(method.getName(),clazz);
				}
			}
		}
	}
	

}
