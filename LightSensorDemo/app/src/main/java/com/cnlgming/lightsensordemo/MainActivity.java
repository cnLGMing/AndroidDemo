package com.cnlgming.lightsensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * @创建者 　　　　cnLGMing
 * @博客 　　　　  www.liuguangmingcn.com
 * @创建时间 　　　16/8/11 17:01
 * @描述 　　　   光线传感器的简单使用
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView      mTvLgiht;
    // 传感器管理
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvLgiht = (TextView) findViewById(R.id.tv_lgiht);
        // 获取传感器管理
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // 为光线传感器设置监听事件
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onDestroy() {
        // 取消光线传感器的监听事件
        mSensorManager.unregisterListener(this);
        super.onDestroy();
    }


    /**
     * 当传感器的数值发生变化时,回调此方法
     *
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // 获取变化的数值
        float[] values = sensorEvent.values;
        mTvLgiht.setText("当前的光线强度为: " + values[0] + " lux");
    }

    /**
     * 当传感器的精度发生变化时,回调此方法
     *
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
