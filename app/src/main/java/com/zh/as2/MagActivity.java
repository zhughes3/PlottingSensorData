package com.zh.as2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MagActivity extends AppCompatActivity implements SensorEventListener {
    PlotView pv ;
    private SensorManager sm;
    private List<Float> readings;
    private Sensor mag;
    private ImageView light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mag);
        readings = new ArrayList<>();
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mag = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
        pv = (PlotView) findViewById(R.id.magpv);
        light = (ImageView) findViewById(R.id.imageView2);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            //All values are in micro-Tesla (uT) and measure the ambient magnetic field in the X, Y and Z axis
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            float value = (x + y + z) / 3;
            Log.v("mag value= ", Float.toString(value));
            readings.add(value);
            float mean = calcMean(readings);
            float var = calcVar(readings, mean);
            Log.v("mag accel= ", Float.toString(var));
            changePicture(var);
            pv.addValue(value);
            pv.addMean(mean);
            pv.addVariance(var);
            pv.invalidate();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void changePicture(float var) {
        if (var > 30.0) {
            light.setImageResource(R.drawable.on);
        } else {
            light.setImageResource(R.drawable.off);
        }
    }

    private float calcMean(List<Float> list) {
        float sum = 0;
        for (float f : list) {
            sum += f;
        }
        return sum/list.size();
    }

    private float calcVar(List<Float> list, float mean) {
        float sumDiffsSquared = 0;

        for (float f : list) {
            float diff = f - mean;
            diff *= diff;
            sumDiffsSquared += diff;
        }

        return sumDiffsSquared / list.size();
    }
}
