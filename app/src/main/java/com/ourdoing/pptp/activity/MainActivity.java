package com.ourdoing.pptp.activity;

import com.ourdoing.pptp.R;
import com.ourdoing.pptp.control.ConnectionConfirm;
import com.xys.libzxing.zxing.activity.*;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;

import com.ourdoing.pptp.control.PageController;


public class MainActivity extends Activity {
    private EditText ip_text;
    private PageController pageController = new PageController();
    private ConnectionConfirm connectionConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        ip_text = (EditText) findViewById(R.id.ipAddr);
    }

    public void setIPAndPort(String _ipAndPort) {
        ip_text.setText(_ipAndPort);
    }

    public String getIPAndPort() {
        return ip_text.getText().toString();
        //return "192.168.1.1:1";

    }

    public void next(View view) {
        sendNext();

    }

    private void sendNext() {
        //// TODO: 输入过滤
        if (getIPAndPort().length() == 0) {
            Toast.makeText(this, "请输入IP", Toast.LENGTH_SHORT).show();
        } else {
            pageController.setIPAndPort(getIPAndPort());

            if ("success".equals(pageController.pageNext())) {//发送成功
                Toast.makeText(this, "向" + getIPAndPort() + "发送了下一页", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "链接异常，请检查网络和防火墙后再试", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void pre(View view) {
        sendPre();

    }

    private void sendPre() {
        //// TODO: 输入过滤
        if (getIPAndPort().length() == 0) {
            Toast.makeText(this, "请输入IP", Toast.LENGTH_SHORT).show();
        } else {
            pageController.setIPAndPort(getIPAndPort());

            if ("success".equals(pageController.pagePre())) {//发送成功
                Toast.makeText(this, "向" + getIPAndPort() + "发送了上一页", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "链接异常，请检查网络和防火墙后再试", Toast.LENGTH_SHORT).show();
            }


        }
    }

    //开始扫描
    public void scanStart(View view) {
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }
    // 扫描完毕之后自动执行此方法，返回扫描结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            this.setIPAndPort(result);

            // 请求连接，等待连接结果
            connectionConfirm = new ConnectionConfirm(this);
            connectionConfirm.execute(getIPAndPort());

        }
    }




    //音量键控制翻页
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            // 音量减小
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                this.sendNext();
                return true;
            // 音量增大
            case KeyEvent.KEYCODE_VOLUME_UP:
                this.sendPre();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void beerClick(View view) {//熊前
        sendPre();
    }

    public void fishClick(View view) {//鱼后
        sendNext();
    }
}
