package com.ourdoing.pptp.control;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ourdoing.pptp.api.MessageList;
import com.ourdoing.pptp.transfer.StringMessageTransfer;

/**
 * Created by BlindingDark on 2016/4/4 0004.
 * 检测是否连接
 */
public class ConnectionConfirm extends AsyncTask<String, String, String> {
    private Activity activity;
    private String ip;
    private String port = "12321";//默认端口
    private StringMessageTransfer stringMessageTransfer = new StringMessageTransfer();

    public ConnectionConfirm(Activity activity) {
        this.activity = activity;
    }

    public String confirm(String ipAndPort) {
        String[] ipPort = ipAndPort.split(":");//以：为分隔符分开IP，前边为地址，后变为端口
        this.ip = ipPort[0];//获得ip地址
        if (ipPort.length > 1) {
            this.port = ipPort[1];//更改端口
        }
        stringMessageTransfer.send(ip, port, MessageList.ready.toString());
        return stringMessageTransfer.receive(port);
    }

    @Override
    protected String doInBackground(String... params) {
        // 请求连接，等待连接结果
        return this.confirm(params[0]);
    }
    @Override
    protected void onPostExecute(String result) {
        if(MessageList.ready.toString().equals(result)) {
            //如果接收到的内容是ready
            Toast.makeText(activity, "发送目标已锁定", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(activity, "链接异常，请检查网络和防火墙后再试", Toast.LENGTH_SHORT).show();
        }
        //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
        //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"

    }
}
