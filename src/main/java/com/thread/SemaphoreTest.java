package com.thread;

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
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore sp = new Semaphore(3); //3个灯
        for (int i = 0; i < 10; i++) { //10个线程。允许同时进3个线程。3个当中某个完成了。外边的7中的一个又可以进去了。【有点像上厕所。5个位子，10个去上厕所。】
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        sp.acquire();//获取灯
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (3 - sp.availablePermits()) + "个并发");
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将离开");

                    sp.release();//释放灯。给其他线程。

                    //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已离开，当前已有" + (3 - sp.availablePermits()) + "个并发");
                }
            };
            service.execute(runnable);
        }
    }

}
