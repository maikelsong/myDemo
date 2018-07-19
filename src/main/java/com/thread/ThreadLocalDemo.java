package com.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalDemo {

    public static ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    public void setThreadLocal(List<String> values) {
        threadLocal.set(values);
    }

    public void getThreadLocal() {
        System.out.println(Thread.currentThread().getName());
        List<String> list = threadLocal.get();
        for(String s : list){
        	System.out.println(s);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final ThreadLocalDemo threadLocal = new ThreadLocalDemo();
        new Thread(new Runnable() {
            List<String> params = new ArrayList<>(3);
			@Override
			public void run() {
				params.add("张三");
	            params.add("李四");
	            params.add("王五");
	            threadLocal.setThreadLocal(params);
	            threadLocal.getThreadLocal();
			}
           
        }).start();

        new Thread(new Runnable() {
            List<String> params = new ArrayList<>(3);
			@Override
			public void run() {
				params.add("111");
	            params.add("222");
	            params.add("333");
	            threadLocal.setThreadLocal(params);
	            threadLocal.getThreadLocal();
			}
           
        }).start();
    }
}