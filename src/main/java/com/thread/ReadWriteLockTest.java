package com.thread;

/*
 * lock比synchronized功能更加强大。
 * 读写锁
 *   多个读锁不互斥，读锁与写锁互斥，写锁与写锁互斥。这是由jvm自己控制的。你只要上好相应的锁即可
 *   
 *   3个线程负责读
 *   3个线程负责写
 *   
 *   都是读不能上锁。因为要提高并发。
 *   
 *   synchronized 这个把’读与读‘也同步互斥了..并发就下去了。
 *   
 */

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Queue3 q3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        q3.get();
                    }
                }

            }.start();

            new Thread() {
                public void run() {
                    while (true) {
                        q3.put(new Random().nextInt(10000));
                    }
                }

            }.start();
        }

    }
}

//不能直接上lock 和 synchronized 了，这样读与读也是互斥的。用它的子类ReadWriteLock
class Queue3 {
    private Object data = null;//共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to read data!");
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + "have read data :" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void put(Object data) {

        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to write data!");
            Thread.sleep((long) (Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " have write data: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }


    }
}
