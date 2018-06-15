package com.thread.test;

/**
 * 这里判断出是否是同一个对象，非常重要。以后用 synchronized（object）{} 的时候
 * 当这个object是同一个对象的时候，才会产生同步互斥的效果。
 * 如果只是'值面上'的相同，也是达不到同步互斥的效果的。
 * <p/>
 * 类说明
 *
 * @author raolesong
 * @version V 1.0 Dec 5, 2011
 */
public class IsSameObject {

    private String key;

    public IsSameObject(String key) {
        this.key = key;  //如果有IsSameObject的2个对象，
        //1 new IsSameObject("1")【如果它们key值相同，它们都是同一个对象】
        //2 new IsSameObject(new String("1"))的形式 【如果它们key值相同，它们也不是是同一个对象】
    }

    public IsSameObject(String key, String key1) {
        this.key = key + key1;  //这里是个两个变量相加,如果有IsSameObject的2个对象， 尽管它们key值相同，它们都不是同一个对象。
    }

    public static void main(String[] args) {

        IsSameObject t1 = new IsSameObject("1");
        IsSameObject t2 = new IsSameObject("1");
        if (t1.key == t2.key) {
            System.out.println("t1 t2的key是同一个对象");
        }


        //这里编译器把它们编译成 "12" ;
        IsSameObject t11 = new IsSameObject("12");
        IsSameObject t22 = new IsSameObject("1" + "2");
        if (t11.key == t22.key) {
            System.out.println("t11 t22的key是同一个对象");
        }


        //t3和t4的key不是同一个对象，首先new String("1") 和 new String("1")是2个不同的对象
        IsSameObject t3 = new IsSameObject(new String("1"));
//		IsSameObject t3 = new IsSameObject("1");
        IsSameObject t4 = new IsSameObject(new String("1"));
        if (t3.key == t4.key) {
            System.out.println("t3 t4的key是同一个对象");
        }


        IsSameObject t5 = new IsSameObject("1", "2"); // ("1"+"")
        IsSameObject t6 = new IsSameObject("1", "2"); // ("1"+"")
        if (t5.key == t6.key) {
            System.out.println("t5 t6的key是同一个对象");
        }


        String a = "1";
        String b = "1";
        String c = "1";
        //因为1是个常量。
        if (a == b) {
            System.out.println("a和b是同一个对象");
        }
        if (a == c) {
            System.out.println("a和c是同一个对象");
        }

        String aa = new String("1");
        String bb = new String("1");
        String cc = new String("1");
        if (aa == bb) {
            System.out.println("aa和bb是同一个对象");
        }
        if (aa == cc) {
            System.out.println("aa和cc是同一个对象");
        }


        String aaa = "1" + "c";
        String bbb = "1" + "c";
        //这里编译器把aaa="1c" ; 所以还是个常量，这里要和上面的变量 “this.key = key+key1;”区别开来
        if (aaa == bbb) {
            System.out.println("aaa和bbb是同一个对象");
        }

    }
}
