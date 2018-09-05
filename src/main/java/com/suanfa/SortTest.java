package com.suanfa;
/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: SortTest.java
 * Author:   raolesong
 * Date:     2016年11月22日 下午5:36:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author raolesong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SortTest {
	
	
	// 插入排序
	public static int[] insertSort(int[] source) {
		//从第2个元素开始比较
		for (int i = 1; i <= source.length - 1; i++) {
			if (source[i] < source[i - 1]) {
				int temp = source[i];
				//依次从后面i-1的位置向前比【因为前面的已经是顺序了】
				for (int j = i - 1; j >= 0; j--) {
					if (temp < source[j]) {
						source[j + 1] = source[j];
						source[j] = temp;
					}
				}
			}
		}
		return source;
	}

	//选择排序
	public static int[] selection_sort(int[] source) {
		return source;
	}
	
	//冒泡排序
	public static int[] maoPao(int[] arr){
	  int  temp, len = arr.length;
      for (int i = 0; i < len - 1; i++){
          for (int j = 0; j < len - 1 - i; j++){
              if (arr[j] > arr[j + 1]) {
                  temp = arr[j];
                  arr[j] = arr[j + 1];
                  arr[j + 1] = temp;
            }
          }    
      }   
	 return arr;
	}
	
	//常用的选择排序方法有简单选择排序和堆排序，这里只说简单选择排序，堆排序后面再说
	//简单选择排序Java实现
	public static int[] SimpleSort(int[] arr) {
        int length=arr.length;
        int temp;
        for(int i=0;i<length-1;i++){
            int min=i;
            for(int j=i+1;j<length;j++){ //寻找最小的数
                if(arr[j]<arr[min]){
                    min =j;
                }
            }
            if(min!=i){
                 temp = arr[min];
                 arr[min]=arr[i];
                 arr[i]=temp;
            }
        }
        return arr;
    }
	
	
	//希尔排序
	public static int[] shellSort(int[] a) {
	    int gap = 1, i, j, len = a.length;
	    int temp;//插入排序交换值的暂存
	    //确定初始步长
	    while (gap < len / 3){
	        gap = gap * 3 + 1;
	    }
	    for (; gap > 0; gap /= 3){//循环遍历步长，最后必为1
	        for (i = gap; i < len; i++) {//每一列依次向前做插入排序
	            temp = a[i];
	            //每一列中在a[i]上面且比a[i]大的元素依次向下移动
	            for (j = i - gap; j >= 0 && a[j] > temp; j -= gap){
	                a[j + gap] = a[j];
	            }
	            //a[i]填补空白，完成一列中的依次插入排序
	            a[j + gap] = temp;
	        }
	    }   
	    return a;
	}
	
	
	
	
	
	
	
	
	//快速排序
	//int[] params = {2,1,5,9,10,0,12,6};
	public static int[] quickSort(int[]arr,int low,int high){
        if (low < high) {     
            int middle = getMiddle(arr, low, high);     
             quickSort(arr, low, middle - 1);            
            quickSort(arr, middle + 1, high);            
         }  
        return arr;
   }
	
   public static int getMiddle(int[] list, int low, int high) {     
       int tmp = list[low];    
       while (low < high) {     
           while (low < high && list[high] >= tmp) {     
               high--;     
           }     
           list[low] = list[high];   
           while (low < high && list[low] <= tmp) {     
               low++;     
           }     
           list[high] = list[low];   
       }     
      list[low] = tmp;           
      return low;                   
   } 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		int[] params = {2,1,5,9,10,0,12,6};
//		int[] newArry =  maoPao(params);
//		printArray(newArry);
		System.out.println("");
//		printArray(SimpleSort(params));
//		printArray(shellSort(params));
		printArray(quickSort(params,0,params.length-1));
		System.out.println("");
		
//		System.out.println(binarySearch0(newArry,0,newArry.length,12));
		
	}

	
	
	
	
	
	
	
	
	
	public static void printArray(int[] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+",");
		}
	}
}
