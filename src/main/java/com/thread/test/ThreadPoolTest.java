package com.thread.test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*创建线程2种方式 

1 new Thread

2  Runnable相当一个任务 【这种更好理解，启动一个线程去执行任务】



*/
public class ThreadPoolTest {

    public static void main(String[] args) {

//		ExecutorService threadPool = Executors.newFixedThreadPool(3);
//		ExecutorService threadPool = Executors.newCachedThreadPool();
    /*	ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for(int i=1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable(){
				@Override
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+",在处理任务,任务编号="+task);
				}
			});
		}
		threadPool.shutdown();*/
//		threadPool.shutdownNow();


    }


}


/*
ExecutorService threadPool = Executors.newFixedThreadPool(3);   只有3个线程处理
pool-1-thread-2,在处理任务,任务编号=2
pool-1-thread-3,在处理任务,任务编号=3
pool-1-thread-1,在处理任务,任务编号=1
pool-1-thread-3,在处理任务,任务编号=5
pool-1-thread-1,在处理任务,任务编号=6
pool-1-thread-2,在处理任务,任务编号=4
pool-1-thread-3,在处理任务,任务编号=7
pool-1-thread-1,在处理任务,任务编号=8
pool-1-thread-2,在处理任务,任务编号=9
pool-1-thread-3,在处理任务,任务编号=10

ExecutorService threadPool = Executors.newCachedThreadPool();  可能会new出10个线程处理
pool-1-thread-1,在处理任务,任务编号=1
pool-1-thread-9,在处理任务,任务编号=9
pool-1-thread-7,在处理任务,任务编号=7
pool-1-thread-5,在处理任务,任务编号=5
pool-1-thread-10,在处理任务,任务编号=10
pool-1-thread-3,在处理任务,任务编号=3
pool-1-thread-8,在处理任务,任务编号=8
pool-1-thread-6,在处理任务,任务编号=6
pool-1-thread-4,在处理任务,任务编号=4
pool-1-thread-2,在处理任务,任务编号=2

ExecutorService threadPool = Executors.newSingleThreadExecutor();	 始终就1个线程处理
pool-1-thread-1,在处理任务,任务编号=1
pool-1-thread-1,在处理任务,任务编号=2
pool-1-thread-1,在处理任务,任务编号=3
pool-1-thread-1,在处理任务,任务编号=4
pool-1-thread-1,在处理任务,任务编号=5
pool-1-thread-1,在处理任务,任务编号=6
pool-1-thread-1,在处理任务,任务编号=7
pool-1-thread-1,在处理任务,任务编号=8
pool-1-thread-1,在处理任务,任务编号=9
pool-1-thread-1,在处理任务,任务编号=10


*/