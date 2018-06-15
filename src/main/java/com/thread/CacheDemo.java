package com.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

    private Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock();


    //相当好 120分  读写互斥，写写互斥。 读读并发--不会破坏数据,系统性能高了。
    //因为多个线程来读的话【如果有数据的话】，不需要互斥。提高并发。多个并发的读。
    public Object getData(String key) {
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                rwl.readLock().unlock();  //当3个线程读锁被释放
                rwl.writeLock().lock();   //(当3个线程读锁被释放)只要第1个线程变成了写锁。如果下面不加if(value==null)条件。可能第2个写锁（有机会）也会去操作【value = "aaaa";】
                try {
                    if (value == null) {    //加上这个if条件非常好。如果当第2个写线程进来。jdk自带的实例也加了这个。
                        value = "aaaa";//实际去查询数据库-->queryDB();
                    }
                } finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }

    //还可以 100分。因为多个线程来读的话【如果有数据的话】，不需要互斥。上面实现更好。【多个来读的话们，这样还是存在同步互斥问题】
    public synchronized Object getData2(String key) {
        Object value = cache.get(key);
        if (value == null) {
            value = "aaaa";//实际去查询数据库-->queryDB();
        }
        return value;
    }

}
