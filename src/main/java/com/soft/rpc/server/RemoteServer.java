package com.soft.rpc.server;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.soft.rpc.client.Call;
import com.soft.rpc.remote.service.api.RemoteHelloService;
import com.soft.rpc.remote.service.impl.RemoteHelloServiceImpl;

public class RemoteServer  {

    private RemoteHelloService remoteService;
    public RemoteServer() {
        remoteService  = new RemoteHelloServiceImpl();
    }
    
    public static void main(String[] args) throws Exception {
        RemoteServer server = new RemoteServer();
        System.out.println("远程服务器启动......DONE!");
        server.service();
    }

    public void service() throws Exception {
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(8001);
        while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new HandlerSocket(socket)).start();
        }
    }

    class HandlerSocket implements Runnable{
    	Socket socket;
    	public HandlerSocket(Socket socket){
    		this.socket = socket; 
    	}
		@Override
		public void run() {
			try{
				InputStream in = socket.getInputStream();
	            ObjectInputStream objIn = new ObjectInputStream(in);
	            OutputStream out = socket.getOutputStream();
	            ObjectOutputStream objOut = new ObjectOutputStream(out);
	            // 对象输入流读取请求的call对象
	            Call call = (Call) objIn.readObject();
	            System.out.println("客户端发送的请求对象：" + call);
	            call = getCallResult(call);
	            // 发送处理的结果回客户端
	            objOut.writeObject(call);
	            objIn.close();
	            in.close();
	            objOut.close();
	            out.close();
	            socket.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
    	
    }
    
    /**
     * 通过反射机制调用call中指定的类的方法，并将返回结果设置到原call对象中
     */
    private Call getCallResult(Call call) throws Exception {
        String className = call.getClassName();
        String methodName = call.getMethodName();
        Object[] params = call.getParams();
        Class<?>[] paramsTypes = call.getParamTypes();

        Class<?> classType = Class.forName(className);
        // 获取所要调用的方法
        Method method = classType.getMethod(methodName, paramsTypes);
        Object result = method.invoke(remoteService, params);
        call.setResult(result);
        return call;
    }

}