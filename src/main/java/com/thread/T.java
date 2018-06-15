/*
 * Copyright (C), 2013-2016, 上汽集团
 * FileName: T.java
 * Author:   raolesong
 * Date:     2016年3月2日 上午11:40:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.thread;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author raolesong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class T {
	
	public static void main(String[] args) {
		
		
		boolean[] used={true,false,false,false,false,false};

	    /*只有当左右手的筷子都未被使用时，才允许获取筷子，且必须同时获取左右手筷子*/
	    System.out.println(used[0]||used[(0+1)%5]);
		
	}

}
