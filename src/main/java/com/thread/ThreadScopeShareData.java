package com.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * 比如数据库连接
 * 张三
 * 		  转出  转入	
 * 王五
 * 
 * 转出和转入操作应该在同一个线程，与线程绑定
 * 
 * 张三和王五两个线程上的connection应该是不同的。【如果相同的话，那王五提交了，也把把张三的commit的】
 * 在线程内共享 【每个线程上的转出和转入的connection是共享的】，在线程外独立【如果相同的话，那王五提交了，也把把张三的commit的】。
 * 
 * 线程范围内的变量
 * 	
 * 我这件事在我这个线程范围内搞定，不要去影响其他线程的事情。
 * 但是我这个线程的事情的每个模块【转出，转入是独立的，但又要共享同一个connection】
 * 
 */

/**
 * 线程范围内的数据共享
 */


public class ThreadScopeShareData {

    private static int data = 0;
    private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            + " has put data :" + data);
                    threadData.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

    static class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }
}
