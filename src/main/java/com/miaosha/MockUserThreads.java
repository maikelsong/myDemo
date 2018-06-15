/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: MockUserThreads.java
 * Author:   raolesong
 * Date:     2018年6月15日 上午11:12:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.miaosha;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.concurrent.CountDownLatch;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年6月15日 上午11:12:56
 */
public class MockUserThreads {
	
	private int threadSize = 100;
	
	private CountDownLatch latch = new CountDownLatch(threadSize);
	
	void start(){
		for(int i=0;i<threadSize;i++){
			Thread th = new Thread(new UserTask());
			th.start();
//			try {
//				th.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
		try {
			System.out.println(latch.getCount()+"<<<<<");
			latch.await();
			System.out.println("===我是主线程===,"+Thread.currentThread().getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class UserTask implements Runnable{
		@Override
		public void run() {
			//具体业务
			System.out.println("具体业务--,"+Thread.currentThread().getId());
			latch.countDown();
		}
	}
	
	public static void main(String[] args) {
		MockUserThreads mock = new MockUserThreads();
		mock.start();
	}

}
