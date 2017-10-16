package com.zh.as2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sm;
    private Sensor mag;
    private List<Sensor> l;
    private List<AccelerometerReading> readings;
    private Sensor accel;
    private TextView accelText;
    private TextView magText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        readings = new ArrayList<>();
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        l = sm.getSensorList(Sensor.TYPE_ALL);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mag = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelText = (TextView) findViewById(R.id.accelText);
        magText = (TextView) findViewById(R.id.magText);
        sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
        if (accel != null) {
            accelText.setText(getSensorInfo(accel, "Accelerometer"));
        }
        if (mag != null) {
            magText.setText(getSensorInfo(mag, "Magnetic Field"));
        }

    }

    private String getSensorInfo(Sensor s, String sensorName) {
        return "Status: " + sensorName + " is Present\n"
                + "Max Range: " + s.getMaximumRange() + "\n"
                + "Resolution: " + s.getResolution() + "\n"
                + "Min Delay: " + s.getMinDelay();
    }

    public void toAccelPlot(View view) {
        Intent intent = new Intent(MainActivity.this, AccelerometerActivity.class);
        startActivity(intent);
    }

    public void toMagPlot(View view) {
        Intent intent = new Intent(MainActivity.this, MagActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
