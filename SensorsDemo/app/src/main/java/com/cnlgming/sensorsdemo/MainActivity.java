package com.cnlgming.sensorsdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView mTvOrientation;
    private TextView mTvGyroscope;
    private TextView mTvMagnetic;
    private TextView mTvGravity;
    private TextView mTvLinearAcceleration;
    private TextView mTvTemperature;
    private TextView mTvLight;
    private TextView mTvPressure;

    // Sensor 管理服务
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 注册传感器监听器

        // 方向传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
        // 陀螺仪传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_UI);

        // 磁场传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_UI);

        // 重力传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_UI);

        // 线性加速度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_UI);

        // 温度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_UI);

        // 光线传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI);

        // 压力传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onDestroy() {
        // 退出时取消传感器监听器
        mSensorManager.unregisterListener(this);
        super.onDestroy();
    }

    private void assignViews() {
        mTvOrientation = (TextView) findViewById(R.id.tv_orientation);
        mTvGyroscope = (TextView) findViewById(R.id.tv_gyroscope);
        mTvMagnetic = (TextView) findViewById(R.id.tv_magnetic);
        mTvGravity = (TextView) findViewById(R.id.tv_gravity);
        mTvLinearAcceleration = (TextView) findViewById(R.id.tv_linear_acceleration);
        mTvTemperature = (TextView) findViewById(R.id.tv_temperature);
        mTvLight = (TextView) findViewById(R.id.tv_light);
        mTvPressure = (TextView) findViewById(R.id.tv_pressure);
    }

    /**
     * 传感器数值改变时的回调
     *
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // 获取变化的传感器对应的数值
        int type = sensorEvent.sensor.getType();
        // 获取变化的值
        float[] values = sensorEvent.values;

        StringBuilder sb = null;
        // 根据不同的传感器做出不同的响应
        switch (type) {
            case Sensor.TYPE_ORIENTATION:
                sb = new StringBuilder();
                sb.append("方向传感器的数值:");
                sb.append("\n绕 X 轴旋转的角度:");
                sb.append(values[0]);
                sb.append("\n绕 Y 轴旋转的角度:");
                sb.append(values[1]);
                sb.append("\n绕 Z 轴旋转的角度:");
                sb.append(values[2]);
                mTvOrientation.setText(sb.toString());
                break;
            case Sensor.TYPE_GYROSCOPE:
                sb = new StringBuilder();
                sb.append("陀螺仪传感器的数值:");
                sb.append("\n绕 X 轴旋转的角速度:");
                sb.append(values[0]);
                sb.append("\n绕 Y 轴旋转的角速度:");
                sb.append(values[1]);
                sb.append("\n绕 Z 轴旋转的角速度:");
                sb.append(values[2]);
                mTvGyroscope.setText(sb.toString());
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sb = new StringBuilder();
                sb.append("磁场传感器的数值:");
                sb.append("\nX 轴上的磁场:");
                sb.append(values[0]);
                sb.append("\nY 轴上的磁场:");
                sb.append(values[1]);
                sb.append("\nZ 轴上的磁场:");
                sb.append(values[2]);
                mTvMagnetic.setText(sb.toString());
                break;
            case Sensor.TYPE_GRAVITY:
                sb = new StringBuilder();
                sb.append("重力传感器的数值:");
                sb.append("\nX 轴方向上的重力:");
                sb.append(values[0]);
                sb.append("\nY 轴方向上的重力:");
                sb.append(values[1]);
                sb.append("\nZ 轴方向上的重力:");
                sb.append(values[2]);
                mTvGravity.setText(sb.toString());
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                sb = new StringBuilder();
                sb.append("线性加速度传感器的数值:");
                sb.append("\nX 轴方向上的线性加速度:");
                sb.append(values[0]);
                sb.append("\nY 轴方向上的线性加速度:");
                sb.append(values[1]);
                sb.append("\nZ 轴方向上的线性加速度:");
                sb.append(values[2]);
                mTvLinearAcceleration.setText(sb.toString());
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("温度传感器的数值:");
                sb.append("\n当前的温度:");
                sb.append(values[0]);
                mTvTemperature.setText(sb.toString());
                break;
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("光线传感器的数值:");
                sb.append("\n当前的光线强度:");
                sb.append(values[0]);
                mTvLight.setText(sb.toString());
                break;
            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("压力传感器的数值:");
                sb.append("\n当前的压力:");
                sb.append(values[0]);
                mTvPressure.setText(sb.toString());
                break;
            default:
                break;
        }
    }

    /**
     * 传感器精度改变时的回调
     *
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
