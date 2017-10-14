package com.zh.as2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sm;
    private Sensor temp;
    private List<Sensor> l;
    private Sensor accel;
    private TextView accelText;
    //private LinearLayout tempLayout = (LinearLayout) findViewById(R.id.tempLayout);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        l = sm.getSensorList(Sensor.TYPE_ALL);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelText = (TextView) findViewById(R.id.accelText);
        sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        if (accel != null) {
            accelText.setText(getSensorInfo(accel));
        }
        //temp = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

    }

    private String getSensorInfo(Sensor s) {
        return s.getName() + "\n" + s.getMaximumRange() + "\n" + s.getResolution() + "\n"
                + s.getMinDelay();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
