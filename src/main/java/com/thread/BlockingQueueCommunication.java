package com.thread;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 抛出异常 特殊值 阻塞 超时
 * 插入 add(e) offer(e) put(e) offer(e, time, unit)
 * 移除 remove() poll() take() poll(time, unit)
 * 检查 element() peek() 不可用 不可用
 * <p/>
 * <p/>
 * 注意只有put和take方法才具有阻塞功能。其他’放‘和’取‘不具备
 * 用2个具有1个空间的队列来实现同步通知的功能。
 * <p/>
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 5, 2011
 */


public class BlockingQueueCommunication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(
                new Runnable() {

                    public void run() {

                        for (int i = 1; i <= 50; i++) {
                            business.sub(i);
                        }

                    }
                }
        ).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }

    }

    static class Business {


        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

        //匿名构造方法。在调任何构造方法之前先执行匿名构造方法。每创建一个对象，就执行一次。而static的只执行一次。
        {
            Collections.synchronizedMap(null); //这是jdk1.5之前线程安全的工具类。有了jdk5之后就用CurrentHashMap
            try {
                System.out.println("xxxxxdfsdsafdsa");
                queue2.put(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void sub(int i) { //注意不能加sychronized，这样会死锁
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void main(int i) { //注意不能加sychronized 这样会死锁
            try {
                queue2.put(1);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            for (int j = 1; j <= 100; j++) {
                System.out.println("main thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
