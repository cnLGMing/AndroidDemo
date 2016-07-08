package com.cnlgming.androidnewpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @创建者 cnLGMing
 * @博客 www.liuguangmingcn.com
 * @创建时间 16/7/8 下午5:11
 * @描述 学习Android 6.0 新的权限机制
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 用于申请权限后的回调
     */
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private EditText mEdNumber;
    private EditText mEdContent;
    private Button   mBtnSend;
    private String   mNumber;
    private String   mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        initListener();
    }

    private void initListener() {
        // 点击后,发送短信
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) { // 表示不同意,需要申请授权

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                } else {
                    // 发送短信
                    sendSms(mNumber, mContent);
                }
            }
        });
    }

    private void initData() {
        mNumber = mEdNumber.getText().toString().trim();
        mContent = mEdContent.getText().toString().trim();
    }

    private void initViews() {
        mEdNumber = (EditText) findViewById(R.id.ed_number);
        mEdContent = (EditText) findViewById(R.id.ed_content);
        mBtnSend = (Button) findViewById(R.id.btn_send);
    }

    /**
     * 发送短信
     *
     * @param number  手机号码
     * @param content 短信内容
     */
    private void sendSms(String number, String content) {
        // 获取短信管理器
        SmsManager smsManager = SmsManager.getDefault();

        // 拆分短信内容（手机短信内容长度不能超过70字）
        ArrayList<String> divideMessages = smsManager.divideMessage(content);
        for (String mes : divideMessages) {
            smsManager.sendTextMessage(number, null, mes, null, null);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 拥有权限,直接发送
                    sendSms(mNumber, mContent);
                } else {
                    // 当弹出的申请权限申请提示框被拒绝时的逻辑
                    Toast.makeText(MainActivity.this, "拒绝了发送短信的权限", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
