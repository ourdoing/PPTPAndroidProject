package com.ourdoing.pptp.transfer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by BlindingDark on 2016/3/29 0029.
 */
public class StringMessageSender {
    private DatagramSocket client;


    public void send(String ip, String sendStr) {
        try {
            client = new DatagramSocket();

            byte[] sendBuf = sendStr.getBytes();
            InetAddress Address = InetAddress.getByName(ip);
            int port = 11751;

            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf, sendBuf.length, Address, port);

            client.send(sendPacket);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
