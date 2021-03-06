package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * lock 比synchronized更加面向对象。锁本身也是个对象。锁的粒度更细了
 * 它是替换synchronized而生的。
 * 
 */
public class LockTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new LockTest().init();
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output("zhangxiaoxiang");
                }

            }
        }).start();

        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output("lihuoming");
                }

            }
        }).start();

    }

    static class Outputer {
        Lock lock = new ReentrantLock();

        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }

        public synchronized void output2(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public static synchronized void output3(String name) {
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
