package com.ourdoing.pptp.control;

import com.ourdoing.pptp.transfer.StringMessageSender;

/**
 * Created by BlindingDark on 2016/3/28 0028.
 */
public class PageController{
    private String ip;
    private StringMessageSender stringMessageSender= new StringMessageSender();

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void pagePre(){
        stringMessageSender.send(ip,"pre");
    }

    public void pageNext(){
        stringMessageSender.send(ip,"next");
    }


}
