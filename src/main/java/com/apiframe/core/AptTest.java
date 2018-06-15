/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: AptTest.java
 * Author:   raolesong
 * Date:     2018年1月5日 下午3:48:04
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.apiframe.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月5日 下午3:48:04
 */
public class AptTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
		System.out.println("==="+context);
		//调用某个方法试试
		
		ApiHander hander = new ApiHander();
		try {
			hander.doRealMethod("findGoodsById",new Long(12L));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
