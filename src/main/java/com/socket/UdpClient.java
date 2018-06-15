package com.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UdpClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            String str = "hello";
            byte[] data = str.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 4567);
            socket.send(packet);


            byte[] rb = new byte[1024];
            //创建一个空的datagramPacket对象,用于接受客户端的数据包
            DatagramPacket p2 = new DatagramPacket(rb, rb.length);
            socket.receive(p2);
            String result = new String(p2.getData(), p2.getOffset(), p2.getLength());
            System.out.println("result--->" + result);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

}
