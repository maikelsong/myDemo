package com.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * ArrayList与linkList（是双向循环链表实现，头节点的前一个是尾节点，尾节点的下一个是头节点）
 * 注意：arraylist是循环删除，从后往前遍历来删除
 * 1.arraylist删除数据，删除前面的数据效率低，删除后面的数据效率高
 * 2.linkList删除数据：删除前面和后面的数据一样高，越往中间走效率越低。
 * 
 * 
 * 
 * 
面试时，其中有这么一道题目：
对已有的2个一维数组，譬如说A[]，B[]，找出2个数组重复的元素。
*/

public class ArrayasListTest1
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
		
		List<Integer> la = Arrays.asList(a);
		List<Integer> lb = Arrays.asList(b);
		
		System.out.println(la.size());
		System.out.println(lb.size());
		
		List result = new ArrayList(); //保存结果
		for (int i=0; i<lb.size(); i++) {
		    if (la.remove(lb.get(i)+"")) {
		    	result.add(lb.get(i));
		    }
		} 
		
		System.out.println(Arrays.toString(result.toArray()));
		
		
		//第2种
		la.retainAll(lb);
		for(int i=0;i<la.size();i++){
			System.out.print(la.get(i)+",");
		}
		
		
	}
	
}
