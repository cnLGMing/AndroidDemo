package com.cnlgming.sensordemo;

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
 * @创建时间 　　　16/8/9 15:15
 * @描述 　　　   加速度传感器的简单实用
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView mTvX;
    private TextView mTvY;
    private TextView mTvZ;

    // 系统是我 Sensor 管理器
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        // 获取系统的传感器管理服务
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 系统的加速度传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        // 取消注册
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    private void assignViews() {
        mTvX = (TextView) findViewById(R.id.tv_x);
        mTvY = (TextView) findViewById(R.id.tv_y);
        mTvZ = (TextView) findViewById(R.id.tv_z);
    }

    /**
     * 此方法是实现了 SensorEventListener 接口必须实现的方法
     * 当传感器的值发生变化时,回调此方法
     *
     * @param sensorEvent   传感器数值的数组
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        mTvX.setText("X轴的加速度为:" + values[0]);
        mTvY.setText("Y轴的加速度为:" + values[1]);
        mTvZ.setText("Z轴的加速度为:" + values[2]);
    }

    /**
     * 此方法是实现了 SensorEventListener 接口必须实现的方法
     * 当传感器精度发生变化时,回调此方法
     *
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
