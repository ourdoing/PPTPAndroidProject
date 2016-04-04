package com.ourdoing.pptp.activity;

import com.ourdoing.pptp.R;
import com.xys.libzxing.zxing.activity.*;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;

import com.ourdoing.pptp.control.PageController;


public class MainActivity extends Activity {
    private EditText ip_text;
    private String iPAndPort = "";
    private PageController pageController = new PageController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        ip_text = (EditText) findViewById(R.id.ipAddr);
    }

    public void setIPAndPort(String _ipAndPort) {
        this.iPAndPort = _ipAndPort;
        ip_text.setText(_ipAndPort);
    }
    public String getIPAndPort() {
        return iPAndPort;
        //return "192.168.1.1:1";

    }

    public void sendNext(View view) {
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

    public void sendPre(View view) {
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


    public void scanStart(View view) {
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            this.setIPAndPort(result);
            Toast.makeText(this, "发送目标已锁定：" + getIPAndPort(), Toast.LENGTH_SHORT).show();
        }
    }

    public void beerClick(View view) {//熊前
        sendPre(view);
    }

    public void fishClick(View view) {//鱼后
        sendNext(view);
    }
}
