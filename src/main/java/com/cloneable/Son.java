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
public class Son implements Cloneable{
	
	private int age;
	
	public Son(int age) {
		super();
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		if(! (obj instanceof Son)){
			return false;
		}
		Son fa = (Son)obj;
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
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Son f1 = new Son(30);
		Son f2 = (Son)f1.clone();
		System.out.println(f1.equals(f2));
		System.out.println(f1==f2);
		System.out.println(f1.getClass().equals(f2.getClass()));
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
