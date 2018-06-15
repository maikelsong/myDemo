package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * （倒数）计数器 ，当归到0的时候，则所有等待着或单个等着开始执行。
 * 它可以实现一个人（也可以是多个人）等待其他所有人都来通知他，可以实现一个人通知多个人的效果，类似裁判一声口令，
 * 运动员同时开始奔跑，所有运动员都跑到终点后裁判才可以公布结果，还可以实现一个计划需要多个领导都签字后才能继续向下
 * 实施的情况。
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 5, 2011
 */
public class CountdownLatchTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "正准备接受命令");
                        cdOrder.await(); //外面3个线程等待着cdOrder归0。当归0了继续向下执行。

                        System.out.println("线程" + Thread.currentThread().getName() +
                                "已接受命令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "回应命令处理结果");
                        cdAnswer.countDown();//每个线程把 cdAnswer 减1
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));

            System.out.println("线程" + Thread.currentThread().getName() +
                    "即将发布命令");
            cdOrder.countDown(); //main主线程把cdOrder归0了。

            System.out.println("线程" + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            cdAnswer.await(); //main主线程等待cdAnswer归0 【相当裁判公布3个运动员的成绩。】

            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到所有响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();

    }
}
