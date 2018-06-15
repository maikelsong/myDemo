package com.Collection;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
面试时，其中有这么一道题目：
对已有的2个一维数组，譬如说A[]，B[]，找出2个数组重复的元素。
*/

public class ArrayasListTest2
{

	
	public static void main(String[] args)
	{
		Integer a[] = {1, 6, 2, 8, 5, 8, 6, 9, 0}; 
		Integer b[] = {4, 5, 4, 8, 7, 6, 2, 0};
		
		//这里千万要注意 
		//创建了一个ArrayList对象，而这个ArrayList并不是java.util包下面的ArrayList，而是java.util.Arrays类中的一个内部类，
		// 它连add,remove等方法都没有：java.lang.UnsupportedOperationException
		//List<Integer> la =  (ArrayList)Arrays.asList(a);
		//Exception in thread "main" java.lang.ClassCastException: java.util.Arrays$ArrayList cannot be cast to java.util.ArrayList
		// Arrays$ArrayList<E> extends java.util.AbstractList
		// java.util.AbstractList<E> extends AbstractCollection<E> implements java.util.List<E>
		
		//需要下面这样构造。千万注意。
		List<Integer> la = new ArrayList<Integer>(Arrays.asList(a));
		List<Integer> lb = new ArrayList<Integer>(Arrays.asList(b));
		
		
		List result = new ArrayList(); //保存结果
		for (int i=0; i<lb.size(); i++) {
		    if (la.remove(lb.get(i))) {
		    	result.add(lb.get(i));
		    }
		} 
		
		System.out.println(Arrays.toString(result.toArray()));
		
		
		//第2种 6,2,8,5,8,6,0,
//		la.retainAll(lb);
		
		//5,8,6,2,0,
		lb.retainAll(la);
		
		for(int i=0;i<lb.size();i++){
//			System.out.print(la.get(i)+",");
			System.out.print(lb.get(i)+",");
		}
		
		
	}
	
}
