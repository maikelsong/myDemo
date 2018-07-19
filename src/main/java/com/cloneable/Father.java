/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: Father.java
 * Author:   raolesong
 * Date:     2018年7月17日 上午9:42:24
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.cloneable;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年7月17日 上午9:42:24
 */
public class Father implements Cloneable{
	
	private int age;
	
	private Son son;
	
	public Father(int age) {
		super();
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		if(! (obj instanceof Father)){
			return false;
		}
		Father fa = (Father)obj;
		if(this.age==fa.age){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int p =31;
		return p*age+super.hashCode();
	}
	
	//浅复制：		//复制说白了，还是生成一个新对象，==表示内存地址的比较
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}
	
	//深复制 		//复制说白了，还是生成一个新对象，==表示内存地址的比较
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Father f = (Father) super.clone();
		Son son = (Son) f.getSon().clone();
		f.setSon(son);
		return f;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Son getSon() {
		return son;
	}

	public void setSon(Son son) {
		this.son = son;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Father f1 = new Father(30);
		Son son = new Son(6);
		f1.setSon(son);
		Father f2 = (Father)f1.clone();
		System.out.println(f1.equals(f2));
		System.out.println(f1==f2);
		//复制说白了，还是生成一个新对象，==表示内存地址
		System.out.println(f1.getSon()==f2.getSon());
		f2.setAge(33);
		son.setAge(8);
		System.out.println("=====");
		
	}
	
}
