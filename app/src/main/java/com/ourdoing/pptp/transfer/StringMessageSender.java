package com.ourdoing.pptp.transfer;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by BlindingDark on 2016/3/29 0029.
 * 向指定IP和PORT发送string数据
 * 使用DatagramSocket进行传送数据，这个类只是一个码头不带有串流
 */
public class StringMessageSender {
    private DatagramSocket client;

    public String send(String ip, String port, String sendStr) {
        try {

            Log.v(ip,port);//调试信息
            client = new DatagramSocket();

            byte[] sendBuf = sendStr.getBytes();//将String 转换为byte类型
            InetAddress Address = InetAddress.getByName(ip);// 获得InetAddress协议地址

            int intPort = Integer.parseInt(port);//端口变为int



            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf, sendBuf.length, Address, intPort);//创建一个有传送数据，数据长度，指定端口，地址的对象

            client.send(sendPacket);//DatagramSocket并不知道数据要发往哪里，具体事宜是根据对象本身决定的所以要传入对象

            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            Log.v(ip, "error");//调试信息
            return "error";
        } finally {
            if (client != null) {
                client.close();//关闭资源
            }
        }
    }

}
