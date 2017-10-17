package com.zh.as2;

/**
 * Created by michael on 10/15/17.
 */

public class AccelerometerReading {
    public long t;
	public float x;
	public float y;
	public float z;

	public AccelerometerReading(long timestamp, float xReading, float yReading, float zReading) {
		t = timestamp;
		x = xReading;
		y = yReading;
		z = zReading;
	}

	public float getAcceleration() {
		float f = x*x + y*y + z*z;
		Double d = Double.parseDouble(new Float(f).toString());
		return (float)Math.sqrt(d);
	}
}
