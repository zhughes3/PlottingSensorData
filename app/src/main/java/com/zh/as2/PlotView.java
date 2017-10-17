package com.zh.as2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 10/4/17.
 */

public class PlotView extends View {
    int MAX = 100;
    int LIST_SIZE = 10;
    List<Float> values = new ArrayList<>(LIST_SIZE);
    List<Float> means = new ArrayList<>(LIST_SIZE);
    List<Float> variances = new ArrayList<>(LIST_SIZE);

    public void clearList() {
        values = new ArrayList<>(LIST_SIZE);
    }

    public void addValue(float f) {
        values.add(f);
        if (values.size() > LIST_SIZE)
            values.remove(0);
    }

    public void addMean(float f) {
        means.add(f);
        if (means.size() > LIST_SIZE) {
            means.remove(0);
        }
    }

    public void addVariance(float f) {
        variances.add(f);
        if (variances.size() > LIST_SIZE) {
            variances.remove(0);
        }
    }

    public PlotView(Context context) {
        super(context);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = this.getWidth(),
                y = this.getHeight(),
                size = values.size(),
                dx = this.getWidth()/LIST_SIZE,
                dy = this.getHeight()/MAX;

        drawCartesianPlane(canvas, x, y);

        for (int i = 0; i < size; i++) {
            Paint p = new Paint();
            p.setColor(Color.RED);
            canvas.drawCircle(i * dx, values.get(i) * dy, 5, p);
            p.setColor(Color.BLUE);
            canvas.drawCircle(i * dx, means.get(i) * dy, 5, p);
            p.setColor(Color.GREEN);
            canvas.drawCircle(i * dx, variances.get(i) * dy, 5, p);
        }

        //invalidate();
    }

    private void drawCartesianPlane(Canvas canvas, int x, int y) {
        Paint axis = new Paint();
        axis.setColor(Color.GRAY);
        canvas.drawLine(0,y-10,x,y-10,axis); //draw x axis
        canvas.drawText("X (time)", x/2, y-5, axis); //label x axis
        //draw horizontal gridlines
        canvas.drawLine(0,y,0,0,axis); //draw y axis
        canvas.drawText("Y", 5, y/2, axis); //label y axis
        //draw vertical gridlines
    }
}
