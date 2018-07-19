package com.study;
public class Bibao{
    public static void main(String[] args) {
        // 做一个简单的演示,把闭包和内部类一起给解释了,其实把资源类用成一个闭包,可以保证资源数据不会错掉
        //通俗点说,就是给一个变量一个生存空间!
        class Res {
            private int sum = 100;
 
            void show1() {
                System.out.println(Thread.currentThread().getName()+"---->"+sum--);
            }
 
            void show2() {
                System.out.println(Thread.currentThread().getName()+">"+sum--);
            }
 
            int getR() {
                return sum;
            }
        }
        final Res r1 = new Res();
        // 像线程这样的东西,随手就可以开启的一个线程,如果去单纯创建一个新的类,是不是有点过于麻烦了呢?
        //这个时候内部类就派上用场了,简单直接!
        new Thread(new Runnable() {
            Res r = r1;
 
            public void run() {
                while (r.getR() > 1)
                    r.show1();
            }
        },"博派") {
 
        }.start();
        // 线程2内部类!
        new Thread(new Runnable() {
            Res r = r1;
             
            public void run() {
                while (r.getR() > 1)
                    r.show2();
            }
        },"狂派") {
             
        }.start();
 
    }
}