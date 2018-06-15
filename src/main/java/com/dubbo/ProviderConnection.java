/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: ProviderConnection.java
 * Author:   raolesong
 * Date:     2018年2月8日 下午5:54:45
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.dubbo;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年2月8日 下午5:54:45
 */
public class ProviderConnection {
	private String PROVIDER_HOST;
	private int PROVIDER_PORT;
	public String getPROVIDER_HOST() {
		return PROVIDER_HOST;
	}
	public void setPROVIDER_HOST(String pROVIDER_HOST) {
		PROVIDER_HOST = pROVIDER_HOST;
	}
	public int getPROVIDER_PORT() {
		return PROVIDER_PORT;
	}
	public void setPROVIDER_PORT(int pROVIDER_PORT) {
		PROVIDER_PORT = pROVIDER_PORT;
	}
	/**
	 * @param pROVIDER_HOST
	 * @param pROVIDER_PORT
	 */
	public ProviderConnection(String pROVIDER_HOST, int pROVIDER_PORT) {
		super();
		PROVIDER_HOST = pROVIDER_HOST;
		PROVIDER_PORT = pROVIDER_PORT;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(PROVIDER_HOST);
		sb.append(":");
		sb.append(PROVIDER_PORT);
		return sb.toString();
	}
}
