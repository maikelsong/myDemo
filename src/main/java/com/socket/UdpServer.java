package com.socket;

//根据TCP协议和UDP协议的不同，在网络编程方面就有面向两个协议的不同socket，一个是面向字节流的一个是面向报文的。

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//Udp协议发送的DatagramPacket数据包,它是一个包一个包接受的。不像tcp有io输入,出流的概念
public class UdpServer implements Runnable {

    public void run() {

        // 1. 构建DatagramSocket实例，指定本地端口。
        DatagramSocket serverSocket = null;

        // 2. 构建需要收发的DatagramPacket报文
        DatagramPacket packet = null;
        try {
            serverSocket = new DatagramSocket(4567);
            byte[] data = new byte[1024];
            //创建一个空的datagramPacket对象,用于接受客户端的数据包
            packet = new DatagramPacket(data, data.length);
            //receive类似于ServerSocket.accept()，会阻塞到一个客户端发送数据才执行它下面的语句
            while (true) {

                serverSocket.receive(packet);
                //数据包不是从0位置开始放置的。它有自己的位偏移量
                String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
                System.out.println("客户端IP:" + packet.getAddress().getHostAddress() + " on port " + packet.getPort());
                System.out.println("接受客户端的数据--->" + result);


                String str = "服务端返回的数据";
                byte[] sb = str.getBytes();
                DatagramPacket p2 = new DatagramPacket(sb, sb.length);
                serverSocket.send(p2);

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new UdpServer());
        thread.start();
    }
}
