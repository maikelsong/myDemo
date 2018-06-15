/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: Registry.java
 * Author:   raolesong
 * Date:     2018年2月7日 下午6:01:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.dubbo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;


/**
 * http://blog.csdn.net/qq_19688339/article/details/78998783
 * @author raolesong
 * 2018年2月7日 下午6:01:10
 */
public class Registry {
	
	public static final int provider_port = 8888;
	private Selector providerSelector;
	
	public static final int consumer_port = 9999;
	private Selector consumerSelector;
	
//	public ConcurrentHashMap<String, ProviderConnection> map = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
	
	/**监听服务提供者的注册请求*/  
    public void register(){  
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(provider_port));
			serverSocketChannel.configureBlocking(false);
			providerSelector = Selector.open();
			serverSocketChannel.register(providerSelector, SelectionKey.OP_ACCEPT);
			System.out.println("监听服务提供者已启动...");
			listenProviderServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void listenProviderServer() throws IOException {
		while (true) {
			//阻塞 ：获取通道内是否有选择器的关心事件，select模型 多路复用
			providerSelector.select();
			Iterator<SelectionKey> iterator = providerSelector.selectedKeys()
					.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				registService(key);
			}
		}
	}
    
    /**provider连接,将接口名\主机名\端口号发送过来;Registry接收到后将信息保存在map中
     * @throws IOException */  
    private void registService(SelectionKey key) throws IOException {  
    	// 接受事件
		if (key.isAcceptable()) {
			System.out.println("服务提供方的客户端连接上了...");
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
					.channel();
			SocketChannel socket = serverSocketChannel.accept();
			socket.configureBlocking(false);
			socket.register(providerSelector, SelectionKey.OP_READ);
		}
		// 读取事件
		else if (key.isReadable()) {
			SocketChannel socket = (SocketChannel) key.channel();
			ByteBuffer dst = ByteBuffer.allocate(4*1024);
			// 非阻塞，传统的InputStream.read是阻塞的
			int read = socket.read(dst);
			if(read>0){
				String[] str = new String(dst.array(), "UTF-8").split("\n");
				map.put(str[0], str[1]); //保存
				System.out.println("服务提供者注册：" + str);
			}else{
				System.out.println("服务提供者客户端关闭");
				key.cancel();
			}
			
		}
    }
    
    /**监听服务消费者的请求*/  
    public void subscribe(){  
    	try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(provider_port));
			serverSocketChannel.configureBlocking(false);
			consumerSelector = Selector.open();
			serverSocketChannel.register(consumerSelector, SelectionKey.OP_ACCEPT);
			System.out.println("监听消费者已启动...");
			listenConsumerServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void listenConsumerServer() throws IOException {
		while (true) {
			//阻塞 ：获取通道内是否有选择器的关心事件，select模型 多路复用
			consumerSelector.select();
			Iterator<SelectionKey> iterator = consumerSelector.selectedKeys()
					.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				subscribeService(key);
			}
		}
	}

    /**接收消费者的信息,并将服务提供者对应的信息返回给消费者*/  
    private void subscribeService(SelectionKey key) throws IOException {  
    	// 接受事件
		if (key.isAcceptable()) {
			System.out.println("消费者新的客户端连接上了...");
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
					.channel();
			SocketChannel socket = serverSocketChannel.accept();
			socket.configureBlocking(false);
			socket.register(consumerSelector, SelectionKey.OP_READ);
		}
		// 读取事件
		else if (key.isReadable()) {
			SocketChannel socket = (SocketChannel) key.channel();
			ByteBuffer dst = ByteBuffer.allocate(4*1024);
			// 非阻塞，传统的InputStream.read是阻塞的
			int read = socket.read(dst);
			if(read>0){
				String interfaceName = new String(dst.array(), "UTF-8");
				String connectionInfo = map.get(interfaceName);
				System.out.println("服务" + interfaceName+",对应的ip,port"+connectionInfo);
				
				dst.clear();
				dst.put(connectionInfo.getBytes());
				dst.flip();
				socket.write(dst);
			}else{
				System.out.println("消费者客户端关闭");
				key.cancel();
			}
			
		}
    }
    
    public static void main(String[] args) throws IOException {
    	Registry registry = new Registry();
    	registry.register();
    	registry.subscribe();
	}
    
}
