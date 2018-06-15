package com.thread.test;

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
 *   synchronized
 *   
 *   
 */

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {

        final Queue q = new Queue();

        for (int i = 0; i < 3; i++) {

            new Thread(new Runnable() {

                public void run() {
                    q.get();
                }

            }).start();

            new Thread(new Runnable() {

                public void run() {
                    q.put("hello");
                }

            }).start();

        }
    }

}

class Queue {

    private String data = "";
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读数据....");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "读到得数据...." + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(String v) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写数据....");
            Thread.sleep(100);
            data = v;
            System.out.println(Thread.currentThread().getName() + "写数据完毕...." + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

	

