package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*创建线程2种方式 

1 new Thread

2  Runnable相当一个任务 【这种更好理解，启动一个线程去执行任务】



*/
public class ThreadPoolTest {

    /**
     * @param args
     */


    public static void main(String[] args) {
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);
//		ExecutorService threadPool = Executors.newCachedThreadPool(); //内部线程数不定。动态变化的
        ExecutorService threadPool = Executors.newSingleThreadExecutor(); //创建一个线程。它的一个好处【当这个线程死掉，池会立即又生成一个新线程出来】
        for (int i = 1; i <= 10; i++) {  //丢10个任务到有3个线程的池中去
            final int task = i;
            //往线程池中添加任务。
            threadPool.execute(new Runnable() {

                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }
        System.out.println("all of 10 tasks have committed! "); //不加shutdownXXX方法，程序并没有结束。因为线程还是活的。
        //threadPool.shutdownNow(); //不等所有任务都完成了。就关闭线程池
        //threadPool.shutdown();	//等所有任务都完成了。就关闭线程池
        /*
		 * 线程池中就2个东西 1->里面有几个线程，2->里面有几个任务。
		 * 现在我们不用直接把任务交给线程执行，我们把任务丢到池中。它们怎么执行我不关心。有空你就去执行，没空就等着。
		 * 
		 * 
		 */

        //这个就相当于java1.5的timer定时器功能，注意是带有 ** Scheduled **的线程池
	/*	Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
				new Runnable(){
					
				public void run() {
					System.out.println("bombing!");
					
				}},
				6,
				2,
				TimeUnit.SECONDS);*/
    }

}
