/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: NioServerSocket.java
 * Author:   raolesong
 * Date:     2018年1月16日 上午11:00:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 
 * 阻塞真正关心的点是：读取是否阻塞
 * this.selector.select(); 阻塞
 * ServerSocket.accept(); 阻塞
 * 
 * Selector 主要负责调度和监控客户端和服务端 
 * 
 * @author raolesong 2018年1月16日 上午11:00:51
 */
public class NioServerSocket {

	// 选择器 - 多路复用器（类似餐厅的服务员，一个服务员可以服务多个客户）
	private Selector selector;

	public static void main(String[] args) throws IOException {
		NioServerSocket nio = new NioServerSocket();
		nio.initServer();
		nio.listenServer();
	}

	public void initServer() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress(8888));
		serverSocketChannel.configureBlocking(false);
		this.selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("服务端已启动...");
	}

	public void listenServer() throws IOException {
		while (true) {
			//阻塞 ：获取通道内是否有选择器的关心事件，select模型 多路复用
			this.selector.select();
			System.out.println("新的客户端连接上了...");
			Iterator<SelectionKey> iterator = this.selector.selectedKeys()
					.iterator();
			
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				handler(key);
			}
		}
	}

	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @param key
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private void handler(SelectionKey key) throws IOException{
		// 接受事件
		if (key.isAcceptable()) {
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
					.channel();
			SocketChannel socket = serverSocketChannel.accept();
			socket.configureBlocking(false);
			socket.register(selector, SelectionKey.OP_READ);
		}
		// 读取事件
		else if (key.isReadable()) {
			SocketChannel socket = (SocketChannel) key.channel();
			ByteBuffer dst = ByteBuffer.allocate(1024);
			// 非阻塞，传统的InputStream.read是阻塞的
			int read = socket.read(dst);
			if(read>0){
				String info = new String(dst.array(), "UTF-8");
				System.out.println("hello>" + info);
			}else{
				System.out.println("客户端关闭");
				key.cancel();
			}
			
		}
	}
	
	//参考 https://www.cnblogs.com/yueweimian/p/6262211.html

}
