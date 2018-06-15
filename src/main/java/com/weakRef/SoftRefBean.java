package com.weakRef;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/*
 * 
 * 
2、强引用
   平时我们编程的时候例如：Object object=new Object（）；那object就是一个强引用了。如果一个对象具有强引用，那就类似于必不可少的生活用品，垃圾回收器绝不会回收它。当内存空 间不足，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足问题。

 

3、软引用（SoftReference）
   如果一个对象只具有软引用，那就类似于可有可物的生活用品。如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存。只 要垃圾回收器没有回收它，该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存。 软引用可以和一个引用队列（ReferenceQueue）联 合使用，如果软引用所引用的对象被垃圾回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。


4、弱引用（WeakReference）   

   如果一个对象只具有弱引用，那就类似于可有可物的生活用品。弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。
   在垃圾回收器线程扫描它 所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程， 因此不一定会很快发现那些只具有弱引用的对象。  弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回 收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。 


 5、虚引用（PhantomReference）   

   "虚引用"顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在 任何时候都可能被垃圾回收。 虚引用主要用来跟踪对象被垃圾回收的活动。虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之 关联的引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。程序如果发现某个虚引用已经被加入到引用队 列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。 

 
 * 
 * 
 * 
 */


public class SoftRefBean {

	public static void main(String[] args) {
		
		Object o=new Object();   
		Object o1=o;  
		o=null;   
		o1=null;
		//如果显式地设置o和o1为null，或超出范围，则gc认为该对象不存在引用，这时就可以收集它了。可以收集并不等于就一会被收集，
		//什么时候收集这要取决于gc的算法，这要就带来很多不确定性。
		//例如你就想指定一个对象，,希望下次gc运行时把它收集了，那就没办法了，有了其他的三种引用就可以做到了。其他三种引用在不妨碍gc收集的情况下，可以做简单的交互。
		
		
//////////////////////////////如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存///////////////////////////////////////////////////////////
		
		String abc=new String("abc");  //1   
		SoftReference<String> abcSoftRef=new SoftReference<String>(abc);  //2   
		abc=null; //4   
		System.out.println("before gc: "+abcSoftRef.get());    
		System.gc(); // 努力来回收未用对象,abc!=null,表示还在用的。所以当abc！=null时候掉gc，对abc没实际作用。   注意体会：和内存空间又是2马事情。
		System.out.println("after gc: "+abcSoftRef.get());  
		
		/*
		      第一行在heap对中创建内容为“abc”的对象，并建立abc到该对象的强引用,该对象是强可及的。

		　　第二行和第三行分别建立对heap中对象的软引用和弱引用，此时heap中的对象仍是强可及的。

		　　第四行之后heap中对象不再是强可及的，变成软可及的。同样第五行执行之后变成弱可及的。
		*/
		
		//软引用是主要用于内存敏感的高速缓存。在jvm报告内存不足之前会清除所有的软引用，这样以来gc就有可能收集软可及的对象，可能解决内存吃紧问题，避免内存溢出。
		/*
		当gc决定要收集软引用是执行以下过程,以上面的abcSoftRef为例：

		　　1、首先将abcSoftRef的referent设置为null，不再引用heap中的new String("abc")对象。

		　　2、将heap中的new String("abc")对象设置为可结束的(finalizable)。

		　　3、当heap中的new String("abc")对象的finalize()方法被运行而且该对象占用的内存被释放， abcSoftRef被添加到它的ReferenceQueue中。

		　　注:对ReferenceQueue软引用和弱引用可以有可无，但是虚引用必须有，参见：
		*/
		
		
////////////////////////在垃圾回收器线程扫描它 所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存////////////////////////////////////////////////////////////////		
//		String abc2=new String("abc");    
//		WeakReference<String> abcWeakRef = new WeakReference<String>(abc2);    
//		abc2=null;    
//		System.out.println("before gc: "+abcWeakRef.get());    
//		System.gc(); // 努力来回收未用对象,abc!=null,表示还在用的。所以当abc！=null时候掉gc，对abc没实际作用。   注意体会：和内存空间又是2马事情。
//		//运行垃圾回收器。 调用 gc 方法暗示着 Java 虚拟机做了一些努力来回收未用对象，以便能够快速地重用这些对象当前占用的内存。当控制权从方法调用中返回时，虚拟机已经尽最大努力从所有丢弃的对象中回收了空间。 
//		System.out.println("after gc: "+abcWeakRef.get());  
		
	}

}
