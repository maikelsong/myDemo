package com.Collection;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

使用二分搜索法来搜索指定的 int 型数组，以获得指定的值。必须在进行此调用之前对数组进行排序（通过 sort(int[]) 方法）。
使用二分搜索法来搜索指定数组，以获得指定对象。在进行此调用之前，必须根据元素的自然顺序对数组进行升序排序（通过 sort(Object[]) 方法）。





返回：
     如果它包含在数组中，则返回搜索键的索引；否则返回 (-(插入点) - 1) 
     。插入点 被定义为将键插入数组的那一点：即第一       个大于此键的
     元素索引，如果数组中的所有元素都小于指定的键，则为 a.length。注意     ，这保证了当且仅当此键被找到时，返回的值将 >= 0。
      否则返回 (-(插入点) - 1)这句话要注意：要是查询的的值小于数组里面
       的最小值那么结果(-(0)-1结果就是-1)，如果查询的 值大于数组里面的       最大值。那么结果就是(-(它的索引值)-1结果就是-(1+索引值))



*/

public class ArrayasTest
{

	
	public static void main(String[] args)
	{
		Integer a[] = {1, 6, 2, 8, 5, 8, 6, 9, 0,-2}; 
		
		Arrays.sort(a);
		
		//Arrays.sort(a) -> [-2, 0, 1, 2, 5, 6, 6, 8, 8, 9]
		
		System.out.println(Arrays.binarySearch(a, 7));
		System.out.println(Arrays.binarySearch(a, -18));
		System.out.println(Arrays.binarySearch(a, 18));
		
		String str = Arrays.deepToString(a);
		System.out.println(str);
		
	}
	
}
