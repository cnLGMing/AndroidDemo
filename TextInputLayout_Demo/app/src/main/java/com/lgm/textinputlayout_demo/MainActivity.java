package com.lgm.textinputlayout_demo;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_username)   EditText          mEtUsername;
    @BindView(R.id.til_username)  TextInputLayout   mTilUsername;
    @BindView(R.id.tiet_password) TextInputEditText mTietPassword;
    @BindView(R.id.til_password)  TextInputLayout   mTilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }


    private void initData() {
        // 设置TextInputLayout提示信息
        mTilUsername.setHint("用户名");
        mEtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 6) {
                    // 设置TextInputLayout显示错误提示
                    mTilUsername.setErrorEnabled(true);
                    // 设置TextInputLayout错误提示消息
                    mTilUsername.setError("用户名不能超过6位！");
                } else {
                    // 设置TextInputLayout隐藏错误提示
                    mTilUsername.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 设置TextInputLayout提示信息
        mTilPassword.setHint("密码");
        mTietPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 6) {
                    // 设置TextInputEditText的错误提示消息
                    mTietPassword.setError("密码不能超过6位！");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
