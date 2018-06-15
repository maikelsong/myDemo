package com.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号灯。
 * 可以维护当前访问自身的线程个数，并提供了同步机制，使用Semaphore可以控制同时
 * 访问资源的线程个数，如，实现一个文件允许的并发访问数。
 * 每个线程要执行需要先取的一个灯。
 * <p/>
 * 单个信号量得semapore对象可以实现‘互斥锁’的功能，并且可以是由一个线程获得了‘锁’，再由另一个
 * 线程释放‘锁’，这可以应用于‘死锁’恢复的一些场合。
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 2, 2011
 */

public class SemaphoreTest {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {

                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println("线程" + Thread.currentThread().getName() + " 进入,当前已有" + (3 - semaphore.availablePermits()) + "个并发");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("线程" + Thread.currentThread().getName() + " 即将离开");

                    semaphore.release();

                    System.out.println("线程" + Thread.currentThread().getName() + " 已离开,当前已有" + (3 - semaphore.availablePermits()) + "个并发");

                }
            };
            threadPool.execute(runnable);
        }


    }

}
