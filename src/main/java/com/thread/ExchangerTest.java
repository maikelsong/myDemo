package com.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以实现2个线程之间的数据交换。
 * 比如 2个毒贩在交换毒品。一个人拿毒品，一个人拿钱
 * <p/>
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 5, 2011
 */

public class ExchangerTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();
        service.execute(new Runnable() {
            public void run() {
                try {

                    String data1 = "zxx";
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger.exchange(data1); //2个线程，有一个先到，2者都到达了，之后才开始交换
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "换回的数据为" + data2);
                } catch (Exception e) {

                }
            }
        });
        service.execute(new Runnable() {
            public void run() {
                try {

                    String data1 = "lhm";
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger.exchange(data1);  //2个线程，有一个先到。2者都到达了，之后才开始交换
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "换回的数据为" + data2);
                } catch (Exception e) {

                }
            }
        });
    }
}
