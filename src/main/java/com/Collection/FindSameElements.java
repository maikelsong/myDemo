package com.Collection;
import java.util.Arrays;
import java.util.HashSet;
 
public class FindSameElements {
 
    /**
     * 获取两个整型数组之间的重复元素集合
     * 
     * @param array1
     *            数组参数1
     * @param array2
     *            数组参数2
     * @return
     */
    public static HashSet findSame(int array1[], int array2[]) {
        HashSet result = new HashSet();// 重复元素结果集合
        HashSet set = new HashSet();// 利用HashSet来寻找重复元素
        for (int i = 0; i < array1.length; i++) {
            set.add(array1[i]);// 把 array1 添加到 set,有过滤作用
        }
 
        for (int i = 0; i < array2.length; i++) {// 遍历第二个数组
            if (!set.add(array2[i])) {// 若有重复元素，add方法返回 false
                result.add(array2[i]);// 将重复出现的元素加入结果集合
            }
        }
        return result;
    }
 
    public static void main(String args[]) {
        int a[] = { 1, 6, 2, 8, 5, 8, 6, 9, 0 };
        int b[] = { 4, 5, 4, 8, 7, 6, 2, 0 };
        // 获取重复元素集合
        HashSet result = findSame(a, b);
        // 遍历输出重复元素
        for (Object o : result) {
             System.out.print(o + " ");
        }
    }
}