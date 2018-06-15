package com.https;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 * 单项握手
 *
 * @author raolesong
 * @version V 1.0 Aug 30, 2012
 */
public class SSLClient1 {

    private static String CLIENT_KEY_STORE = "C:/Documents and Settings/raolesong/client_ks";

    public static void main(String[] args) throws Exception {
        // Set the key store to use for validating the server cert.
        System.setProperty("javax.net.ssl.trustStore", CLIENT_KEY_STORE);

        System.setProperty("javax.net.debug", "ssl,handshake");

        SSLClient1 client = new SSLClient1();
        Socket s = client.clientWithoutCert();

        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s
                .getInputStream()));
        writer.println("hello>>>");
        writer.flush();
        System.out.println(reader.readLine());
        s.close();
    }

    private Socket clientWithoutCert() throws Exception {
        SocketFactory sf = SSLSocketFactory.getDefault();
        Socket s = sf.createSocket("localhost", 8443);
        return s;
    }
}
