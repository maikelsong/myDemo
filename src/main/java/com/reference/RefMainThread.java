package com.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 
 * 
 * 
引用类型	取得目标对象方式	垃圾回收条件	是否可能内存泄漏
强引用	直接调用		不回收	可能
软引用				视内存情况回收	不可能
弱引用	通过 get() 	 永远回收	不可能   [一般用weak reference引用的对象是有价值被cache]
虚引用	无法取得		不回收	可能

 * 
 * 不同 Java 虚拟机上的表现与分析
让我们来回顾一下四种引用类型的表现以及在垃圾回收器回收清理内存时的表现 .

软引用 (SoftReference), 引用类型表现为当内存接近满负荷 , 或对象由 SoftReference.get() 方法的调用没有发生一段时间后 , 垃圾回收器将会清理该对象 . 在运行对象的 finalize 方法前 , 会将软引用对象加入 ReferenceQueue 中去 .
弱引用 (WeakReference), 引用类型表现为当系统垃圾回收器开始回收时 , 则立即会回收该对象的引用 . 与软引用一样 , 弱引用也会在运行对象的 finalize 方法之前将弱引用对象加入 ReferenceQueue.
强引用 (FinalReference), 这是最常用的引用类型 . JVM 系统采用 Finalizer 来管理每个强引用对象 , 并将其被标记要清理时加入 ReferenceQueue, 并逐一调用该对象的 finalize() 方法 .
虚引用 (PhantomReference), 这是一个最虚幻的引用类型 . 无论是从哪里都无法再次返回被虚引用所引用的对象 . 虚引用在系统垃圾回收器开始回收对象时 , 将直接调用 finalize() 方法 , 但不会立即将其加入回收队列 . 只有在真正对象被 GC 清除时 , 才会将其加入 Reference 队列中去 .
 * 
 */

public class RefMainThread {
	public static void main(String[] args) {
		// 创建三种不同的引用类型所需对象
		RefTestObj softRef = new RefTestObj();
		RefTestObj weakRef = new RefTestObj();
		RefTestObj phanRef = new RefTestObj();

		softRef.setId(1);
		weakRef.setId(2);
		phanRef.setId(3);

		ReferenceQueue<RefTestObj> softRefQueue = new ReferenceQueue<RefTestObj>();
		ReferenceQueue<RefTestObj> weakRefQueue = new ReferenceQueue<RefTestObj>();
		ReferenceQueue<RefTestObj> phanRefQueue = new ReferenceQueue<RefTestObj>();

		SoftReference<RefTestObj> softRefObj = new SoftReference<RefTestObj>(
				softRef, softRefQueue);
		WeakReference<RefTestObj> weakRefObj = new WeakReference<RefTestObj>(
				weakRef, weakRefQueue);
		PhantomReference<RefTestObj> phanRefObj = new PhantomReference<RefTestObj>(
				phanRef, phanRefQueue);

		// 打印正常情况下三种对象引用
		print(softRefObj);
		print(weakRefObj);
		print(phanRefObj);

		// 将对象清空
		softRef = null;
		weakRef = null;
		phanRef = null;

		// 打印引用队列及 get() 方法所能取到的对象自身
		if (softRefObj != null) {
			System.out.println("Soft Reference Object run get():"
					+ softRefObj.get());
			System.out.println("Check soft queue:" + softRefQueue.poll());
		}

		if (weakRefObj != null) {
			System.out.println("Weak Reference Object run get():"
					+ weakRefObj.get());
			System.out.println("Check weak queue:" + weakRefQueue.poll());
		}

		if (phanRefObj != null) {
			System.out.println("Phantom Reference Object run get():"
					+ phanRefObj.get());
			System.out.println("Check Phantom queue:" + phanRefQueue.poll());
		}

		// 开始执行垃圾回收
		System.gc();
		System.runFinalization();

		// 检查队列，是否已经被加入队列，是否还能取回对象
		if (softRefObj != null) {
			System.out.println("Soft Reference Object run get():"
					+ softRefObj.get());
			System.out.println("Check soft queue:" + softRefQueue.poll());
		}

		if (weakRefObj != null) {
			System.out.println("Weak Reference Object run get():"
					+ weakRefObj.get());
			System.out.println("Check weak queue:" + weakRefQueue.poll());
		}

		if (phanRefObj != null) {
			System.out.println("Phantom Reference Object run get():"
					+ phanRefObj.get());
			System.out.println("Check Phantom queue:" + phanRefQueue.poll());
		}

		// 对于虚引用对象，在经过多次 GC 之后， 才会加入到队列中去
		Reference<? extends RefTestObj> mynewphan = null;
		int refCount = 1;
		while (mynewphan == null) {
			mynewphan = phanRefQueue.poll();
			System.gc();
			System.runFinalization();
			if (mynewphan != null) {
				System.out.println("Check Phantom queue:" + mynewphan);
				System.out.println("Count for " + refCount + " times");
				break;
			}
			refCount++;
		}
	}

	public static void print(Reference<RefTestObj> ref) {
		RefTestObj obj = ref.get();
		System.out.println("The Reference is " + ref.toString()
				+ " and with object " + obj + " which is "
				+ (obj == null ? "null" : "not null"));
	}
}