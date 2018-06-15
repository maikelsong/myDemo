/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: ServiceConsumer.java
 * Author:   raolesong
 * Date:     2018年2月7日 下午6:00:34
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.dubbo;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年2月7日 下午6:00:34
 */
public class ServiceConsumer {
	
	 /**从注册中心获取服务提供者的信息: 
     * 1.host服务提供者的主机名 
     * 2.port服务提供者的端口号 
     * */  
    public void getProvider(String interfaceName){  
    	
    }

    /** 
     * 利用动态代理的方式获取服务 
     *  1.服务消费者端持有代理类对象; 
     *  2.当消费者执行代理对象的方法时,建立和服务提供者的连接, 
     *  3.发送相关参数给服务提供者, 
     *  4.获得方法执行后的结果 
     * @param serviceInterface :服务的接口类型 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public <T>T findService(Class<T> serviceInterface){  
    	return null;
    }
}
