/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: Hello.java
 * Author:   raolesong
 * Date:     2018年1月22日 下午2:30:27
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.proxy;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月22日 下午2:30:27
 */
public class Hello implements IHello {

	@Override
	public void sayHello() {
		System.out.println("hello ...");
	}

}
