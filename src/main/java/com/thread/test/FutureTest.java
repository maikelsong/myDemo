/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: FutureTest.java
 * Author:   raolesong
 * Date:     2016年7月14日 下午5:55:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author raolesong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FutureTest {
	
	
	int doother(){
		try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("正在执行。。。");
		return 1;
	}
	
	public static void main(String[] args) {
		
		ExecutorService exe = Executors.newSingleThreadExecutor(); 
		
		Future<Integer> future = exe.submit(new Callable(){
			@Override
			public Integer call() throws Exception {
				int result = new FutureTest().doother();
				return result;
			}
		});
		
		try {
			System.out.println("执行结果="+future.get());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(! future.isDone()){
			System.out.println("等待执行");
		}
		
		for(;;){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("iiiii");
		}
		
	}

}
