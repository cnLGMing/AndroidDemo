package com.lgm.floatingactionbutton_demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton mFabLeftTop;
    private FloatingActionButton mFabRightTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initData() {
        // 为右上角的悬浮按钮设置图标
        mFabRightTop.setImageDrawable(getDrawable(R.drawable.ic_loyalty_white_24dp));

        // 为左上角的悬浮按钮设置点击监听
        mFabLeftTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFabRightTop.isShown()) {    // 右上角的悬浮按钮是否已经显示
                    mFabRightTop.hide(); // 若显示，则隐藏
                } else {
                    mFabRightTop.show(); // 否隐藏，则显示
                }
            }
        });

        // 为右上角的悬浮按钮设置点击监听
        mFabRightTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFabLeftTop.isShown()) {    // 左上角的悬浮按钮是否已经显示
                    mFabLeftTop.hide(); // 若显示，则隐藏
                } else {
                    mFabLeftTop.show(); // 否隐藏，则显示
                }
            }
        });
    }

    private void initViews() {
        mFabLeftTop = (FloatingActionButton) findViewById(R.id.fab_left_top);
        mFabRightTop = (FloatingActionButton) findViewById(R.id.fab_right_top);
    }

}
