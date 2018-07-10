package com.soft.rpc.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 负责创建链接
 */
public class Connector {
    private Socket linksocket;
    private InputStream in;
    private ObjectInputStream objIn;
    private OutputStream out;
    private ObjectOutputStream objOut;

    public Connector(){}
    /**
     * 创建链接
     */
    public void connect(String host, Integer port) throws UnknownHostException, IOException {
        linksocket = new Socket(host, port);
        in = linksocket.getInputStream();
        out = linksocket.getOutputStream();
        objOut = new ObjectOutputStream(out);
        objIn = new ObjectInputStream(in);
    }
    /**
     * 发送请求call对象
     */
    public void sendCall(Call call) throws IOException {
        objOut.writeObject(call);
    }
    /**
     * 获取请求对象
     */
    public Call receive() throws ClassNotFoundException, IOException {
        return (Call) objIn.readObject();
    }
    /**
     * 简单处理关闭链接
     */
    public void close() {
        try {
            linksocket.close();
            objIn.close();
            objOut.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}