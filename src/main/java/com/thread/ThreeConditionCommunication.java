package com.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//老大 > 老2 > 老3 【老3 > 老大 >>>>>>】  总共来回50次
public class ThreeConditionCommunication {

    /**
     * @param args
     */
    public static void main(String[] args) {

        final Business business = new Business();

        //如果直接用main线程的for--》 要注意它们三者放的位置。如果这样放的话。只执行一次就死锁了。因为执行完第1次for[shouldSub=2]。执行第2个for，这个时候 while(shouldSub != 1){condition1.await
        //因为for是一步步走下去的。
        //而其他2个线程还没开始执行。所以这样的代码应该先把所以该起的线程都先启动起来【把for放在他们2个线程之后】。或则用下面这种方式，不去依赖main线程
        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }

        //我们不依赖main线程。而是创建一个新线程的。绝对比依赖main线程安全。其实最好也把这段放在最下面为好。
//		new Thread(
//				new Runnable() {
//					
//					
//					public void run() {
//						for(int i=1;i<=50;i++){
//							business.main(i);
//						}
//					}
//				}
//		).start();


        new Thread(
                new Runnable() {


                    public void run() {
                        for (int i = 1; i <= 50; i++) {
                            business.sub2(i);
                        }
                    }
                }
        ).start();


        new Thread(
                new Runnable() {


                    public void run() {
                        for (int i = 1; i <= 50; i++) {
                            business.sub3(i);
                        }

                    }
                }
        ).start();

    }

    static class Business {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        private int shouldSub = 1;

        public void sub2(int i) {
            lock.lock();
            try {
                while (shouldSub != 2) {
                    try {
                        condition2.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub2 thread sequence of " + j + ",loop of " + i);
                }
                shouldSub = 3;
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub3(int i) {
            lock.lock();
            try {
                while (shouldSub != 3) {
                    try {

                        condition3.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 20; j++) {
                    System.out.println("sub3 thread sequence of " + j + ",loop of " + i);
                }
                shouldSub = 1;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (shouldSub != 1) {
                    try {
                        condition1.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 100; j++) {
                    System.out.println("main thread sequence of " + j + ",loop of " + i);
                }
                shouldSub = 2;
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }

    }
}
