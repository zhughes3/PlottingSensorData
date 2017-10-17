package com.zh.as2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

// move everything to here for the accelerometer readings
public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    PlotView pv ;
    private SensorManager sm;
    private List<Float> readings;
    private Sensor accel;
    private ImageView light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        pv = (PlotView) findViewById(R.id.pv);
        light = (ImageView) findViewById(R.id.imageView);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        readings = new ArrayList<>();
        sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            AccelerometerReading reading = new AccelerometerReading(
                    System.currentTimeMillis(),
                    event.values[0],
                    event.values[1],
                    event.values[2]);
            float f = reading.getAcceleration();
            //Log.v("Accel ", Float.toString(f));
            readings.add(f);
            float mean = calcMean(readings);
            float variance = calcVar(readings, mean);
            //Log.v("Var=", Float.toString(variance));
            changePicture(variance);

            pv.addValue(f);
            pv.addMean(mean);
            pv.addVariance(variance);
            pv.invalidate();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void changePicture(float variance) {
        if (variance > 20) {
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
