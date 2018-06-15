package com.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 有点像小区里修的车子的”坡“，（路撞） 用来减速的。
 * <p/>
 * 比如约定20名同学一起坐车去旅游。先到的等待。最后一个来了才开始。
 * <p/>
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 5, 2011
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(3); //表示创建3个线程
        for (int i = 0; i < 3; i++) { //创建3个线程
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点1，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await(); //前2个线程等到第3个执行到这里。才继续往下面执行。

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点2，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await(); //前2个线程等到第3个执行到这里。才继续往下面执行。


                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await();        //前2个线程等到第3个执行到这里。才继续往下面执行。
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
