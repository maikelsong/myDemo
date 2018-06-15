/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: ServiceProvider.java
 * Author:   raolesong
 * Date:     2018年2月7日 下午6:00:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.dubbo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.io.NioServerSocket;

/**
 * 〈功能详细描述〉
 * 
 * @author raolesong 2018年2月7日 下午6:00:03
 */
public class ServiceProvider {
	
	public static final String registoryHost = "127.0.0.1";
	public static final int registoryPort = 8888;
	
	public static final String providerHost = "127.0.0.1";
	public static final int providerPort = 8889;
	private Selector providerSelector;
	
	public ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

	/**将服务保存在本地
	 * @throws IOException */  
    public void regist(Object object)throws IOException {  
    	Class[] cls = object.getClass().getInterfaces();
    	String interfaceName=cls[0].getName();
    	map.put(interfaceName, object);
    	System.out.println(interfaceName+"保存...");  
    	registService(interfaceName);
    }
    
    /**将服务的接口名和服务提供者的信息注册到远程的注册中心*/  
    private void registService(String interfaceName)throws IOException{  
    	SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(registoryHost,registoryPort));
    	ByteBuffer src = ByteBuffer.allocate(4*1024);
    	src.put(interfaceName.getBytes());
    	src.put("\n".getBytes());
    	src.put(new ProviderConnection(providerHost,providerPort).toString().getBytes());
    	
    	src.flip();
    	socketChannel.write(src);
    	System.out.println(interfaceName+"注册...");  
    }
    
    boolean isRuning = true;
    
    /**启动服务器 --等待消费者来调用*/  
    public void start(){  
    	while(isRuning){
    		try {
    			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    			serverSocketChannel.bind(new InetSocketAddress(providerPort));
    			serverSocketChannel.configureBlocking(false);
    			providerSelector = Selector.open();
    			serverSocketChannel.register(providerSelector, SelectionKey.OP_ACCEPT);
    			System.out.println("监听消费者调用已启动...");
    			listenProviderServer();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
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
				handlerConsumer(key);
			}
		}
	}
    
    /** 
     * 1.服务消费者连接成功之后,接收消费者发送来的信息: 
     *      1).服务名称:接口名 
     *      2).方法名 
     *      3).参数类型(Class[]) 
     *      4).参数对象(Object[]) 
     * 2.调用方法 
     *      method.invoke(...); 
     * 3.返回: 
     *      方法执行后的result对象. 
     * */  
    private void handlerConsumer(SelectionKey key) throws IOException {  
    	// 接受事件
		if (key.isAcceptable()) {
			System.out.println("消费者直连上了...");
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

    /**关闭服务器*/  
    public void stop(){  
    	isRuning = false;
    }
    
    
    public static void main(String[] args) {
    	ServiceProvider serviceProvider = new ServiceProvider();
    	try {
			serviceProvider.regist(new HelloWorld());
			serviceProvider.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
