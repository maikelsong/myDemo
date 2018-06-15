package com.thread.test;

import java.util.Random;

public class ThreadLocalTest {

    public final static ThreadLocal<Integer> integerLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {

                public void run() {
                    int data = new Random().nextInt();
                    String name = "hello" + data;
                    MyThreadScopeData.getThreadInstance().setAge(data);
                    MyThreadScopeData.getThreadInstance().setName(name);
                    System.out.println("当前线程:" +
                            Thread.currentThread().getName() + " data:" + data);
                    integerLocal.set(data);
                    p();
                }
            }).start();
        }
    }


    static void p() {
        int data = integerLocal.get();
        System.out.println("获取的线程:" +
                Thread.currentThread().getName() + " data:" + data
                + ",年龄：" + MyThreadScopeData.getThreadInstance().getAge()
                + ",姓名：" + MyThreadScopeData.getThreadInstance().getName());

    }


}

class MyThreadScopeData {
    private String name;
    private int age;
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();

    private MyThreadScopeData() {
    }

    public static MyThreadScopeData getThreadInstance() {
        if (map.get() == null) {
            map.set(new MyThreadScopeData());
        }
        return map.get();
    }

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
