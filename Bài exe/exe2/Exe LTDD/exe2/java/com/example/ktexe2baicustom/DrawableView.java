package com.example.ktexe2baicustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawableView extends View {
    public DrawableView(Context context) {
        super(context);
    }

    private float khoangcach = 1;
    public void changer(float i){
        if(i > 9)
        {
            khoangcach = i  * 10 ;
        }else if(i < 9)
        {
            khoangcach = i  * 10;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        canvas.drawCircle(getMeasuredWidth()/2 + khoangcach,getMeasuredHeight()/2 + khoangcach, 300, paint1);
        canvas.drawCircle(getMeasuredWidth()/2 + khoangcach,getMeasuredHeight()/2 + khoangcach, 290, paint);
        canvas.drawCircle(getMeasuredWidth()/2 - 150 + khoangcach,getMeasuredHeight()/2 - 150 + khoangcach, 45, paint1);
        canvas.drawCircle(getMeasuredWidth()/2 + 150 + khoangcach,getMeasuredHeight()/2 - 150 + khoangcach, 45, paint1);
        canvas.drawCircle(getMeasuredWidth()/2 + khoangcach,getMeasuredHeight()/2 + 120 + khoangcach, 45, paint1);
        float[] floats1 = new float[floats.size()];
        int j = 0;
        for (float f : floats){
            floats1[j] = f;
            j++;
        }
        canvas.drawLines(floats1, paint1);
        super.onDraw(canvas);
    }

    private ArrayList<Float> floats = new ArrayList<>();

    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                floats.add(event.getX());
                floats.add(event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                invalidate();
                break;
        }
        return true;
    }

}
