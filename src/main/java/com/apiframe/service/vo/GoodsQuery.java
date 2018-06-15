/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: GoodsQuery.java
 * Author:   raolesong
 * Date:     2018年1月18日 下午3:32:14
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.apiframe.service.vo;

import java.io.Serializable;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月18日 下午3:32:14
 */
public class GoodsQuery implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 3309833140511814049L;
	
	private long id;
	
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
