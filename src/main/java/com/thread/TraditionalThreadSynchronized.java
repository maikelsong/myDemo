package com.thread;

public class TraditionalThreadSynchronized {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output("zhangxiaoxiang");
                }

            }
        }).start();

        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output2("lihuoming");
                }

            }
        }).start();

    }

    static class Outputer {

        //字节码也是一种对象
        public void output(String name) {
            int len = name.length();
//			synchronized (this) //是用当前对象作为锁对象
            synchronized (Outputer.class) //是用字节码作为锁对象 和上面的this有区别
            {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        public synchronized void output2(String name) { //是用当前对象作为锁对象
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        public static synchronized void output3(String name) { //是用字节码作为锁对象 因为它是静态方法
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
