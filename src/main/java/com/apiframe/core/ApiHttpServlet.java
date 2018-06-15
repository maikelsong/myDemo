/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: ApiHttpServlet.java
 * Author:   raolesong
 * Date:     2018年1月5日 下午2:43:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.apiframe.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月5日 下午2:43:15
 */
public class ApiHttpServlet extends HttpServlet {

	/**
	 */
	private static final long serialVersionUID = -8930659062993031941L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
