package com.singleton;
/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: Single.java
 * Author:   raolesong
 * Date:     2016年2月17日 上午11:07:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 *  这里有几个原因关于为什么在Java中宁愿使用一个枚举量来实现单例模式：
       1、 自由序列化；
       2、 保证只有一个实例（即使使用反射机制也无法多次实例化一个枚举量）；
       3、 线程安全；
 *
 */
public enum SingletonEnum {
	
	 INSTANCE {

	        @Override
	        protected void read() {
	            System.out.println("read");
	        }

	        @Override
	        protected void write() {
	            System.out.println("write");
	        }

	    };
	    
	    protected abstract void read();
	    protected abstract void write();

}

/*
 * 反编译后的类：
 * public abstract class Singleton extends Enum
{

    private Singleton(String s, int i)
    {
        super(s, i);
    }

    protected abstract void read();

    protected abstract void write();

    public static Singleton[] values()
    {
        Singleton asingleton[];
        int i;
        Singleton asingleton1[];
        System.arraycopy(asingleton = ENUM$VALUES, 0, asingleton1 = new Singleton[i = asingleton.length], 0, i);
        return asingleton1;
    }

    public static Singleton valueOf(String s)
    {
        return (Singleton)Enum.valueOf(singleton/Singleton, s);
    }

    Singleton(String s, int i, Singleton singleton)
    {
        this(s, i);
    }

    public static final Singleton INSTANCE;
    private static final Singleton ENUM$VALUES[];

    static 
    {
        INSTANCE = new Singleton("INSTANCE", 0) {

            protected void read()
            {
                System.out.println("read");
            }

            protected void write()
            {
                System.out.println("write");
            }

        };
        ENUM$VALUES = (new Singleton[] {
            INSTANCE
        });
    }
}

看到了这个类的真身过后，相信很多人对于枚举单例防反射的的原理就了解了：

类的修饰abstract，所以没法实例化，反射也无能为力。
关于线程安全的保证，其实是通过类加载机制来保证的，我们看看INSTANCE的实例化时机，是在static块中，JVM加载类的过程显然是线程安全的。
对于防止反序列化生成新实例的问题还不是很明白，一般的方法我们会在该类中添加上如下方法，不过枚举中也没有显示的写明该方法。

 * 
 * 
 */





