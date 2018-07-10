/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: DemoApplication.java
 * Author:   raolesong
 * Date:     2018年7月5日 上午10:50:02
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.launch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.BeanLifeTest;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年7月5日 上午10:50:02
 */
public class DemoApplication {
	
	public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext(
		          "/config/application.xml");
		 BeanLifeTest test1 = (BeanLifeTest) context.getBean("beanTest1");
	      System.out.println("========"+test1);
	      
	 	 BeanLifeTest test2 = (BeanLifeTest) context.getBean("beanTest1");
	      System.out.println("========"+test2);
	      
	      System.out.println(new BeanLifeTest());
	      System.out.println(new BeanLifeTest());
	}

}
