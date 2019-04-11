package com.example.xcs.xcsdemo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xcs on 2018-04-19.
 */

public class PieView extends View {
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private float mStartAngle = 0;
    private List<UserData> userDatas;
    Paint mPaint;
    int mWidth,mHeight;
    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (userDatas == null){
            return;
        }
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth/ 2, mHeight/2);
        float r = (float) (Math.min(mWidth,mHeight) /4 * 0.8);
        RectF rectF = new RectF(-r,-r,r,r);

        for (int i = 0; i< userDatas.size(); i++){
            UserData data = userDatas.get(i);
            mPaint.setColor(data.getColor());
            canvas.drawArc(rectF, currentStartAngle, data.getAngle(),true,mPaint);



            currentStartAngle += data.getAngle();

        }
        for (int i = 0; i< userDatas.size(); i++){
            UserData data = userDatas.get(i);
            mPaint.setColor(data.getColor());
            float startX, startY,lastAngle = 0;
            if (i == 0) {
                startX = (float) (rectF.centerX() + r * Math.cos( data.getAngle() / 2 * Math.PI / 180));
                startY = (float) (rectF.centerY() + r * Math.sin(data.getAngle() / 2 * Math.PI / 180));
            }else {
                for (int j = i; j > 0; j--){
                    lastAngle += userDatas.get(j -1).getAngle();
                }
                startX = (float) (rectF.centerX() + r * Math.cos(( lastAngle + data.getAngle()/ 2 )* Math.PI / 180));
                startY = (float) (rectF.centerY() + r * Math.sin((lastAngle + data.getAngle() / 2 )* Math.PI / 180));
            }
            mPaint.setColor(Color.BLACK);
            canvas.drawLine(0,0,startX,startY ,mPaint);

            Log.d("线段坐标点：",  startX + "-" + startY);
        }
    }

    public void setData(List<UserData> data){
        this.userDatas = data;
        initData(userDatas);
        invalidate();
    }

    // 初始化数据
    private void initData(List<UserData> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            UserData pie = mData.get(i);

            sumValue += pie.getValue();       //计算数值和

            int j = i % mColors.length;       //设置颜色
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            UserData pie = mData.get(i);

            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pie.setPercent(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;

            Log.i("angle", "" + pie.getAngle());
        }
    }
}
