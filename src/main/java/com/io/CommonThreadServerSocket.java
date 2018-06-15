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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * telnet 10.47.16.110 8888
 * 
 * @author raolesong 2018年1月16日 上午10:06:01
 */
public class CommonThreadServerSocket {

	public static void main(String[] args) throws IOException {
		
		final ServerSocket serverSocket = new ServerSocket(8888);
		
		ExecutorService threadPool = Executors.newCachedThreadPool();

		while (true) {
			// 阻塞1，等待客户端
			final Socket socket = serverSocket.accept();
			System.out.println("新的客户端连接上了...");
			//每一个客户端取一起线程去处理
			threadPool.submit(new Runnable() {
				public void run() {
					try {
						handler(socket);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @param socket
	 * @throws IOException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private static void handler(Socket socket) throws IOException {
		InputStream inStream = socket.getInputStream();
		byte[] buff = new byte[1024];
		//阻塞2 inStream.read(buff)
		while((inStream.read(buff))>0){
			String info = new String(buff,"UTF-8");
			System.out.println("hello>"+info);
		}
	}
}
