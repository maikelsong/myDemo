/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: Fanxing.java
 * Author:   raolesong
 * Date:     2018年7月17日 下午3:16:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.study;

import java.util.*;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年7月17日 下午3:16:29
 */
public class Fanxing {
	
	/*
	class Super{
    }
    class Self extends Super{
    }
    class Son extends Self{
    }

    public static void main(String[] args) {
		
        List<? extends Self> a = new ArrayList<>();//参数类型上界是Self
        a.add(new Son());//error 不能放入任何类型，因为编译器只知道a中应该放入Self的某个子类，但具体放哪种子类它并不知道，因此，除了null以外，不能放入任何类型
        a.add(new Self());//error
        a.add(new Super());//error
        a.add(null);//ok
        Self s1 = a.get(0); //返回类型是确定的Self类，因为<? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，但是入参类型无法确定，只能接受null的传入
        Super s2 = a.get(0); //Self类型可以用Super接收
        Son s3 = a.get(0); //error:子类不能接收父类型参数

        //--------------------------------------

        List<? super Self> b = new ArrayList<>();//参数类型下界是Self
        b.add(new Son());//ok 只能放入T类型，且满足T类型的超类至少是Self，换句话说，就是只能放入Self的子类型
        b.add(new Self());//ok 本身类型也可以
        b.add(new Super());//ok 超类不可以
        b.add(null);//ok
        Object o1 = b.get(0);//返回类型是未知的， 因为<? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
        Son o2 = b.get(0);//error
        Self o3 = b.get(0);//error
        Super o4 = b.get(0);//error

        List<?> c = new ArrayList<>();
        c.add(new Object());
        c.add(null);
        Object o9 = c.get(0);
        //总结：
        // 1. <? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，但是入参类型无法确定，只能接受null的传入
        // 2. <? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
        // 3. ? 既不能用于入参也不能用于返参
	*/

}
