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
    List<Float> points = new ArrayList<>(LIST_SIZE);

    public void clearList() {
        points = new ArrayList<>(LIST_SIZE);
    }

    public void addPoint(float f) {
        points.add(f);
        if (points.size() > LIST_SIZE)
            points.remove(0);
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

        Paint p = new Paint(Color.BLUE);

        int x = this.getWidth(),
                y = this.getHeight(),
                size = points.size(),
                dx = this.getWidth()/LIST_SIZE,
                dy = this.getHeight()/MAX;


        for (int i = 0; i < size; i++) {
            canvas.drawCircle(i * dx, points.get(i) * dy, 5, p);
        }

        //invalidate();
    }
}
