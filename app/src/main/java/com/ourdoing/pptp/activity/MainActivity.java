package com.ourdoing.pptp.activity;

import com.xys.libzxing.zxing.activity.*;
import com.xys.libzxing.zxing.encoding.*;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ourdoing.pptp.R;
import com.ourdoing.pptp.control.PageController;


public class MainActivity extends AppCompatActivity {
    private EditText ip_text;
    private PageController pageController = new PageController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        ip_text = (EditText) findViewById(R.id.ipAddr);
    }

    public String getIP() {
        return ip_text.getText().toString();
    }

    public void sendNext(View view) {
        //// TODO: 输入过滤
        if (getIP().length() == 0) {
            Toast.makeText(this, "请输入IP", Toast.LENGTH_SHORT).show();
        } else {
            pageController.setIp(getIP());
            pageController.pageNext();
            Toast.makeText(this, "向" + getIP() + "发送了下一页", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendPre(View view) {
        //// TODO: 输入过滤
        if (getIP().length() == 0) {
            Toast.makeText(this, "请输入IP", Toast.LENGTH_SHORT).show();
        } else {
            pageController.setIp(getIP());
            pageController.pagePre();
            Toast.makeText(this, "向" + getIP() + "发送了上一页", Toast.LENGTH_SHORT).show();
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
            ip_text.setText(result);
        }
    }
}
