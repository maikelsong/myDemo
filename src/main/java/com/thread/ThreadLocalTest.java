package com.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围内的数据共享
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Nov 30, 2011
 *          一个ThreadLocal代表一个变量，故其中里只能放一个数据，你有两个变量都要线程范围内共享，则要定义2个ThreadLocal对象，以此类推
 *          或者你还可以打包数据（把数据放在一个对象里MyData，
 *          但要注意）
 *          这个MyData最好设计成这样：【和单例很相似，但不可能是单例的，因为每个Thread里有单独的MyData对象】  在这个Mydata对象里在设计一个ThreadLocal，参考以下写法
 *          【线程范围内共享的对象】
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();
    private static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<MyThreadScopeData>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            + " has put data :" + data);
                    x.set(data);
/*					MyThreadScopeData myData = new MyThreadScopeData();
                    myData.setName("name" + data);
					myData.setAge(data);
					myThreadScopeData.set(myData);*/
                    MyThreadScopeData.getThreadInstance().setName("name" + data);
                    MyThreadScopeData.getThreadInstance().setAge(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            int data = x.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
/*			MyThreadScopeData myData = myThreadScopeData.get();;
			System.out.println("A from " + Thread.currentThread().getName() 
					+ " getMyData: " + myData.getName() + "," +
					myData.getAge());*/
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " getMyData: " + myData.getName() + "," +
                    myData.getAge());
        }
    }

    static class B {
        public void get() {
            int data = x.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);
            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " getMyData: " + myData.getName() + "," +
                    myData.getAge());
        }
    }
}

/**
 * strut2也是这样的设计思想。每个请求是一个线程，把请求的数据放在一个容器里，每个容器的数据都是不相关的。
 * <p/>
 * 一个客户端请求， tomcat就启动一个请求线程
 * 有1W个请求，如果没有清除机制， tomcat一个月下来，可能里面有100W或更多的请求
 * 所以每个线程结束的时候， 应该remove掉当条
 * ThreadLocal 的remove的时候
 * 我们不手动掉remove，
 * 它ThreadLocal内部也会清掉的【
 * 每个线程都保持对其线程局部变量副本的隐式引用，只要线程是活动的并且 ThreadLocal 实例是可访问的；在线程消失之后，
 * 其线程局部实例的所有副本都会被垃圾回收（除非存在对这些副本的其他引用）。
 * <p/>
 * 怎么理解关闭浏览器就是关闭当前一个线程
 * <p/>
 * 】
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Nov 30, 2011
 */
class MyThreadScopeData {
    private MyThreadScopeData() {
    }

    public static /*synchronized*/ MyThreadScopeData getThreadInstance() {
        MyThreadScopeData instance = map.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }

    //private static MyThreadScopeData instance = null;//new MyThreadScopeData();
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
