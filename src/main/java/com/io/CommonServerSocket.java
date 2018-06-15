/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: CommonServerSocket.java
 * Author:   raolesong
 * Date:     2018年1月16日 上午10:06:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月16日 上午10:06:01
 */
public class CommonServerSocket {
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888);
		while(true){
			//阻塞1，等待客户端
			Socket socket = serverSocket.accept();
			System.out.println("新的客户端连接上了...");
			InputStream inStream = socket.getInputStream();
			byte[] buff = new byte[1024];
			//阻塞2 inStream.read(buff)
			while((inStream.read(buff))>0){
				String info = new String(buff,"UTF-8");
				System.out.println("hello>"+info);
			}
			
		}
	}
}
