package com.thread.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//不能改动此Test类	
public class Test extends Thread {

    private TestDo testDo;
    private String key;
    private String value;

    public Test(String key, String key2, String value) {
        this.testDo = TestDo.getInstance();
        /*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
		以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
        this.key = key + key2;  //这里是个两个变量相加
/*		a = "1"+"";
		b = "1"+""
		它们是同一个对象。编译器就会把a=“1” b=“1”
*/
        this.value = value;
    }


    public static void main(String[] args) throws InterruptedException {
        Test a = new Test("1", "", "1");
        Test b = new Test("1", "", "2");
        Test c = new Test("3", "", "3");
        Test d = new Test("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();

    }

    public void run() {
        testDo.doSome(key, value);
    }
}

class TestDo {

    private TestDo() {
    }

    private static TestDo _instance = new TestDo();

    public static TestDo getInstance() {
        return _instance;
    }

    private ArrayList keys = new ArrayList();

    //	private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();
    public void doSome(Object key, String value) {
        Object o = key;
        if (!keys.contains(o)) {
            keys.add(o); //如果直接用new ArrayList()可能会出现问题的。4个线程最终都是运行doSome方法。
            //这个方法里面有了对集合迭代。也有对集合数据的修改。。会产生ConcurrentModificationException【这个异常并不是每次都能出现的，
            //要一个线程刚好在迭代，另一个线程刚好在add。这个异常才出现的】。所以用CopyOnWriteArrayList代替ArrayList


        } else { //如果有的话。【它们只是值面上相同。但不是同一个对象。用synchronized就达不到效果】，所以我们要去寻找与这个值’key‘相等的那个对象。

            for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
                try {
                    Thread.sleep(20); //故意加上，在我迭代的过程中，等待别人来修改集合
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Object oo = iter.next();
                if (oo.equals(o)) {
                    o = oo; //让o指向老的那个对象。以达到互斥的效果
                    break;
                }
            }
        }
        synchronized (o)
        // 以大括号内的是需要局部同步的代码，不能改动!
        {
            try {
                Thread.sleep(1000);
                System.out.println(key + ":" + value + ":"
                        + (System.currentTimeMillis() / 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

