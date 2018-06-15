package com.thread.test;

import java.util.concurrent.Semaphore;

//执行完4个子线程，再去执行main线程

public class Test44  {
	
	public static void main(String[] args) throws InterruptedException
	{
		
		final Semaphore semaphore = new Semaphore(1);
		
		semaphore.acquire();
		
		new Thread(new Runnable(){
			@Override
			public void run()
			{
				semaphore.release();
				for(int i=0;i<10;i++){
					System.out.println(Thread.currentThread().getName()+"----,"+i);
				}
			}
			
		}).start();
		
		
		new Thread(new Runnable(){
			@Override
			public void run()
			{
				for(int i=0;i<10;i++){
					System.out.println(Thread.currentThread().getName()+"+++,"+i);
				}
			}
			
		}).start();
		
		
		
	}

}


