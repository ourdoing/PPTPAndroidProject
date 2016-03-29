package com.ourdoing.pptp.activity;

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
        pageController.setIp(getIP());
        pageController.pageNext();
        Toast.makeText(this, "你点击了next", Toast.LENGTH_LONG).show();
    }

    public void sendPre(View view) {
        pageController.setIp(getIP());
        pageController.pagePre();
        Toast.makeText(this, "你点击了pre", Toast.LENGTH_LONG).show();
    }


}
