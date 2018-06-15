package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 实现功能，
 * sub（每次循环10次） 和 main（每次循环100次） 交替循环
 * 1 先实现sub和main的完全互斥。
 * 2 添加交替功能
 * 
 * 
 * 
 * 
 */

public class TraditionalThreadCommunication {

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

}

/**
 * sleep wait 其实两者都可以让线程暂停一段时间,但是本质的区别是一个线程的运行状态控制,一个是线程之间的通讯的问题
 * <p/>
 * sleep和wait的区别有：
 * 1，这两个方法来自不同的类分别是Thread和Object
 * 2，最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
 * 3，wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在
 * 任何地方使用
 * synchronized(x){
 * x.notify()
 * //或者wait()
 * }
 * 4,sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 6, 2011
 */

class Business {
    private boolean bShouldSub = true;
    //sub和main用的是同一把锁 Business this这个对象

    public synchronized void sub(int i) {
        //if也可以， 一般这里用while，防止伪唤醒【在没有被通知、中断或超时的情况下，线程还可以唤醒一个所谓的虚假唤醒 】
          /*伪唤醒==> 在没有被通知、中断或超时的情况下，线程还可以唤醒一个所谓的虚假唤醒 (spurious wakeup)。
		  虽然这种情况在实践中很少发生，但是应用程序必须通过以下方式防止其发生，即对应该导致该线程被提醒的条件进行测试
		  ，如果不满足该条件，则继续等待。换句话说，等待应总是发生在循环中
		  */
        while (!bShouldSub) {
            try {
                this.wait(); //它会释放锁得。 cpu运行到sub，但sub很遵守规则 当发现bShouldSub为false的时候，sub功能先等待， 此时进入main，等main运行玩100次得时候，此时bShouldSub为true，
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false; //sub需遵守规则，但很可能main已经在wait，该main执行，但main已经在wait了，它不会反推代码。 所以还有唤醒main！
        this.notify(); //为了唤醒main
    }

    public synchronized void main(int i) {
        //if也可以， 一般这里用while，防止伪唤醒
        while (bShouldSub) {
            try {
                this.wait(); //cpu运行到main，但mian很遵守规则 当发现bShouldSub为true的时候，main功能先等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++) {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;  //main需遵守规则
        this.notify();
    }
}
