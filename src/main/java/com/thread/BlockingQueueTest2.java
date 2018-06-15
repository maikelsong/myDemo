/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: BlockQueueTest2.java
 * Author:   raolesong
 * Date:     2016年7月5日 上午11:11:47
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author raolesong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BlockingQueueTest2 {
	
	BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
	
	void produce(){
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep(200);
							System.out.println(Thread.currentThread().getName()+"准备放数据");
							queue.put("1");
							System.out.println(Thread.currentThread().getName()+"已放数据完毕,队列有"+queue.size()+"个数据");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}	
			}.start();
		}
	}
	
	void consume(){
		for(int i=0;i<1;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep(1000);
							System.out.println(Thread.currentThread().getName()+"准备取取数据");
							queue.take();
							System.out.println(Thread.currentThread().getName()+"已取去数据完毕,队列有"+queue.size()+"个数据");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}	
			}.start();
		}
	}
	
	public static void main(String[] args) {
		BlockingQueueTest2 t2 = new BlockingQueueTest2();
		t2.produce();
		t2.consume();
	}
	

}
