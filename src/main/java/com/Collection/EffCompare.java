package com.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * ClassName:EffCompare.java
 * Author: Jack0511
 * CreateTime: Feb 28, 2011 2:50:23 PM
 * Description:
 **/

public class EffCompare {
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>(10000);
     for(int i=0;i<100000;i++){
       list.add("jack"+i);
     }
     
     String[] array = list.toArray(new String[10000]);
     Arrays.sort(array);
     
     long begin = System.currentTimeMillis();
     for(int k=60000;k<70000;k++){  //1
       int index = Arrays.binarySearch(array, "jack"+k);
       //System.out.println(Arrays.binarySearch(array, "jack"+k));
     }
     long end = System.currentTimeMillis();
     System.out.println("二分法查找10000个item消耗："+(end-begin)+"毫秒");
     
     
     begin = System.currentTimeMillis();
     for(int n=60000;n<70000;n++){  //2
       boolean isExist = list.contains("jack"+n);
       //System.out.println(list.contains("jack"+n));
     }
     end = System.currentTimeMillis();
     System.out.println("contains查找10000个item消耗："+(end-begin)+"毫秒");
  }

}

//http://blog.csdn.net/jack0511/article/details/6213543