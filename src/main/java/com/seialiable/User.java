package com.seialiable;

import java.io.Serializable;

public class User implements Serializable {
    /**
	 */
	private static final long serialVersionUID = 8842412599707930136L;
	private String userName;
    private String password;
    private String sex;
    
    private transient String str ;
    
    //全参构造方法、get和set方法省略
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @param userName
	 * @param password
	 * @param sex
	 */
	public User(String userName, String password, String sex,String str) {
		super();
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.str = str;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
    
    
}