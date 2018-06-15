package com.apiframe.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: GoodsEntity.java
 * Author:   raolesong
 * Date:     2018年1月5日 下午2:02:16
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

/**
 * 表实体
 * @author raolesong
 * 2018年1月5日 下午2:02:16
 */
@Entity(name = "t_goods")
public class GoodsEntity implements Serializable {
	
	private static final long serialVersionUID = -6084490546962909563L;

	/**表主键**/
	private long id;
	
	/**商品名称**/
	private String name;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	

}
