/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: Hook.java
 * Author:   raolesong
 * Date:     2018年2月3日 下午2:37:34
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.ShutdownHook;

/**
 * 〈功能详细描述〉
 * 
 * @author raolesong 2018年2月3日 下午2:37:34
 */
public class Hook {

	public static void main(String[] args) {
		// SpringApplication.run(App.class, args);
		int c = 0, r = 0;
		c = 'H';
		r = 'r';
		System.out.println(c);
		System.out.println(r);

		Runtime.getRuntime()
				.addShutdownHook(new Thread(new Hook().new ShutdownHook()));
		
		System.out.println("+++++++++");

	}

	class ShutdownHook implements Runnable {

		@Override
		public void run() {
			while(true){
				System.out.println("i am shutdown...");
			}
		}

	}

}
