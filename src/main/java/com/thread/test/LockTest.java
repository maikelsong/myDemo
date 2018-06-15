package com.thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * lock 比synchronized更加面向对象。锁本身也是个对象。
 * 它是替换synchronized而生的。
 * 
 */
public class LockTest {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                public void run() {
                    outPut2("aaaaa");
                }

            }).start();


            new Thread(new Runnable() {
                public void run() {
                    outPut2("bbbbb");
                }

            }).start();

        }
    }

    synchronized static void outPut(String arg) {
        for (int i = 0; i < arg.length(); i++)
            System.out.print(arg.charAt(i));
        System.out.println("");
    }

    static void outPut2(String arg) {
        lock.lock();
        try {
            for (int i = 0; i < arg.length(); i++)
                System.out.print(arg.charAt(i));
            System.out.println("");
        } finally {
            lock.unlock();
        }
    }

}
