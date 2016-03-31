package com.ourdoing.pptp.control;

import com.ourdoing.pptp.transfer.StringMessageSender;

/**
 * Created by BlindingDark on 2016/3/28 0028.
 * 控制页面翻页
 */
public class PageController {
    private String ip;
    private String port = "12321";//默认端口
    private StringMessageSender stringMessageSender = new StringMessageSender();

    public void setIPAndPort(String ipAndPort) {
        String[] ipPort = ipAndPort.split(":");
        this.ip = ipPort[0];
        if (ipPort.length > 1) {
            this.port = ipPort[1];
        }


    }

    public void pagePre() {
        stringMessageSender.send(ip, port, "pre");
    }

    public void pageNext() {
        stringMessageSender.send(ip, port, "next");
    }


}
