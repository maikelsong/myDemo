package com.singleton;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: Singleton2.java
 * Author:   raolesong
 * Date:     2016年2月18日 下午4:13:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

/**
 * 在CJC(一) 中提到一个问题,即 readResolve方法是干啥的? 当时也没多想, 只是列在那里, 今天忙里偷闲地把搜点材料整理下这个问题.
　　原来这个方法跟对象的序列化相关(这样倒是解释了为什么 readResolve方法是private修饰的). ??? 怎么跟对象的序列化相关了?
下面我们先简要地回顾下对象的序列化. 一般来说, 一个类实现了 Serializable接口, 我们就可以把它往内存地写再从内存里读出而"组装"成一个跟原来一模一样的对象.
 不过当序列化遇到单例时,这里边就有了个问题: 从内存读出而组装的对象破坏了单例的规则. 单例是要求一个JVM中只有一个类对象的, 而现在通过反序列化,一个新的对象克隆了出来.
 *
 *当把 MySingleton对象(通过getInstance方法获得的那个单例对象)序列化后再从内存中读出时,
 * 就有一个全新但跟原来一样的MySingleton对象存在了. 那怎么来维护单例模式呢?这就要用到readResolve方法了. 如下所示:
 *
 *这样当JVM从内存中反序列化地"组装"一个新对象时,就会自动调用这个 readResolve方法来返回我们指定好的对象了, 单例规则也就得到了保证.
 *
 */
public final class Singleton2 implements Serializable {
	
	private Singleton2() {
	}

	private static final Singleton2 INSTANCE = new Singleton2();

	public static Singleton2 getInstance() {
		return INSTANCE;
	}

	//这个方法
//	private Object readResolve() throws ObjectStreamException {
//		// instead of the object we're on,
//		// return the class variable INSTANCE
//		return INSTANCE;
//	}
	
	public static void main(String[] args) {
		Singleton2 st = new Singleton2();
		
		try{
			FileOutputStream fos = new FileOutputStream("d:/2.txt");
			ObjectOutputStream ois = new ObjectOutputStream(fos);
			ois.writeObject(st);
			fos.close();
			ois.close();
		}catch(Exception e){
		}
		
		try{
			FileInputStream fis = new FileInputStream("d:/2.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Singleton2 s1 = (Singleton2)ois.readObject();
			
			FileInputStream fis2 = new FileInputStream("d:/2.txt");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			Singleton2 s2 = (Singleton2)ois2.readObject();
			
			fis.close();
			ois.close();
			
			fis2.close();
			ois2.close();
			
			System.out.println(s1==s2);
			
			
		}catch(Exception e){}
		
		
	}
}
