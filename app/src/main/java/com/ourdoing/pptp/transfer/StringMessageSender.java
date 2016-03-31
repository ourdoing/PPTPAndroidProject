package com.ourdoing.pptp.transfer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by BlindingDark on 2016/3/29 0029.
 * 向指定IP和PORT发送string数据
 */
public class StringMessageSender {
    private DatagramSocket client;

    public void send(String ip, String port, String sendStr) {
        try {
            client = new DatagramSocket();

            byte[] sendBuf = sendStr.getBytes();
            InetAddress Address = InetAddress.getByName(ip);

            int intPort = Integer.parseInt(port);

            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf, sendBuf.length, Address, intPort);

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
