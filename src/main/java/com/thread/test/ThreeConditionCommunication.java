package com.thread.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//老大 > 老2 > 老3 【老3 > 老大 >>>>>>】  总共来回50次
public class ThreeConditionCommunication {

    public static void main(String[] args) {

        final Business business = new Business();

        //这样写会产生死锁的,当前这个线程被await，都没有被唤醒的机会。因为它只有一个执行线程，所以最好放在3个线程执行
        /*
		new Thread(new Runnable(){
			
			public void run() {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=1;i<=50;i++){
					business.sub2(i);
					business.sub3(i);
					business.sub1(i);
				}
			}
		}).start();*/


        for (int i = 1; i <= 50; i++) {

            final int index = i;

//			business.sub3(index); //  不要依赖main线程 直接创建一个线线程。

            new Thread(new Runnable() {

                public void run() {
                    System.out.println("111");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    business.sub3(index);
                }
            }).start();

            new Thread(new Runnable() {

                public void run() {
                    System.out.println("222");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    business.sub2(index);
                }
            }).start();

            new Thread(new Runnable() {

                public void run() {
                    System.out.println("333");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    business.sub1(index);
                }
            }).start();

        }
		
		
		/*
		new Thread(new Runnable(){
			
			public void run() {
				for(int i=1;i<=50;i++)
					business.sub1(i);
			}
		}).start();
		
		new Thread(new Runnable(){
			
			public void run() {
				for(int i=1;i<=50;i++)
					business.sub2(i);
			}
		}).start();
		
		
		for(int i=1;i<=50;i++)
			business.sub3(i);*/

    }

    static class Business {

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        int shouldDo = 1;

        void sub1(int m) {
            lock.lock();
            try {
                if (shouldDo != 1) {
                    try {
                        condition1.await();  //执行到这里【释放锁并等待信号】，120的代码是不会执行的。await是阻塞的。并释放了该锁。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println("sub1:" + i + " loop of:" + m);
                }
                shouldDo = 2;
                condition2.signal();
            } finally {
                System.out.println("unlockaaaa");
                lock.unlock();
            }
        }

        void sub2(int m) {
            lock.lock();
            try {
                if (shouldDo != 2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println("sub2:" + i + " loop of:" + m);
                }
                shouldDo = 3;
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }

        void sub3(int m) {
            lock.lock();
            try {
                if (shouldDo != 3) {
                    try {
                        System.out.println("aaaa");
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println("sub3:" + i + " loop of:" + m);
                }
                shouldDo = 1;
                condition1.signal();
            } finally {
                System.out.println("unlockaaaa");
                lock.unlock();
            }
        }
    }

}
