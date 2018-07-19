package com.study;

import com.study.Two.Three;

public class One {
    //静态属性和静态方法是否可以被继承？是否可以被重写？以及原因？
    public static String one_1 = "one";
    public static void oneFn() {
        System.out.println("oneFn");
    }
    
    public static void main(String[] args) {
		System.out.println(Three.one_1);
	}
    
    public String name = null;
    
    public void sayClass(){
    	class InClass{ //局部内部类
    		String name2 = "10";
    		void say(){
    			
    		}
    	}
    }
}