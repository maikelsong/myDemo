package com.thread;

/**
 * 多个线程访问共享对象和数据的方式
 * <p/>
 * 原则：
 * 1 如果每个线程执行的代码相同。可以使用同一个Runnable对象，这个Runnable对象中有那个共享数据，如卖票系统【10个窗口卖100张票】
 * 2 如每个线程执行的代码不同【如：设计4个线程，2个对j相加，2个对j减少。】，这时需要用不同的Runnable对象，有如下方式来实现这些runnable对象之间的数据共享
 * <p/>
 * a 将共享数据封装在另一个对象中，能后将这个对象逐一传递给各个runnable对象，每个线程对共享数据的操作方法也分配到那个对象
 * 上去完成，这样容易实现针对该对象的各个操作的互斥和通信。
 * <p/>
 * b 将这些runnable对象作为某一个类中的内部类，共享数据作为这个外部类中的成员变量，每个线程对共享的操作方法也分配给外部类
 * 以便实现对共享数据进行的各个操作的互斥和通信，作为内部类的各个runnable对象调用外部类的这些方法。
 * <p/>
 * c 将a b方法组合。将共享数据封装在另一个对象中，每个线程对共享数据的操作方法也分配到那个对象身上去完成，对象作为这个外部类
 * 中的成员变量或方法中的局部变量，每个线程的runnable对象作为外部类中的成员内部类或局部内部类
 * <p/>
 * 总之 要同步互斥的几段代码最好是分别放在几个独立的方法中，这些方法在放在同一类中，这样比较容易实现它们之间的同步互斥和通信。
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Nov 30, 2011
 */

public class MultiThreadShareData {

    private static ShareData1 data1 = new ShareData1();

    public static void main(String[] args) {
//		方法a
        ShareData1 data2 = new ShareData1();
        new Thread(new MyRunnable1(data2)).start();
        new Thread(new MyRunnable2(data2)).start();

        final ShareData1 data1 = new ShareData1();
        new Thread(new Runnable() {

            public void run() {
                data1.decrement();

            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                data1.increment();

            }
        }).start();

    }

}

class MyRunnable1 implements Runnable {
    private ShareData1 data1;

    public MyRunnable1(ShareData1 data1) {
        this.data1 = data1;
    }

    public void run() {
        data1.decrement();

    }
}

class MyRunnable2 implements Runnable {
    private ShareData1 data1;

    public MyRunnable2(ShareData1 data1) {
        this.data1 = data1;
    }

    public void run() {
        data1.increment();
    }
}

class ShareData1 /*implements Runnable*/ {
/*		private int count = 100;

		public void run() {
			// TODO Auto-generated method stub
			while(true){
				count--;
			}
		}*/


    private int j = 0;

    public synchronized void increment() {
        j++;
    }

    public synchronized void decrement() {
        j--;
    }
}

//方法b..............................
class ShareDataByRaolesong {

    private int i;

    public synchronized void decrement() {
        i--;
    }

    public synchronized void increment() {
        i++;
    }

    public void doit() {
        for (int m = 0; m < 2; m++) {
            new Thread(new in()).start();
            new Thread(new de()).start();
        }
    }


    class in implements Runnable {

        public void run() {
            increment();
        }
    }

    class de implements Runnable {

        public void run() {
            decrement();
        }
    }


}
	
	
	
	
	
	