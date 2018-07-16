package com.example.jeremylau.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jeremylau on 2018/7/13.
 */
public class SiderBarMenu extends View {

    //画笔
    private Paint mSideBarMenuPaint;
    //定义一个用户点击item标识
    private int click = -1;
    //侧边导航栏的文字
    private String[] SIDEBAR = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //显示tips的控件
    private TextView mTvTips;


    public SiderBarMenu(Context context) {
        this(context, null);
    }

    public SiderBarMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SiderBarMenu(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        init();
    }


    private void init() {
        mSideBarMenuPaint = new Paint();
        //设置画笔颜色
        mSideBarMenuPaint.setColor(Color.RED);
        //设置字体大小
        mSideBarMenuPaint.setTextSize(30);
        //设置字体
        mSideBarMenuPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取控件的宽、高度，即你在布局文件当中设置的：layout_width、layout_height两个的值;
        int width = getWidth();
        int height = getHeight();
        //每个Item高度
        int SideBarItemHeight = height / SIDEBAR.length;
        for (int i = 0; i < SIDEBAR.length; i++) {
            //计算我们要画文字的X坐标，计算公式：控件宽度/2-文字宽度/2 目的：文字水平方向居中
            float xPos = width / 2 - mSideBarMenuPaint.measureText(SIDEBAR[i]) / 2;
            //计算我们要画文字的Y坐标，计算公式：控件高度*当前项数+控件高度/2 目的：文字垂直方向居中
            float yPos = SideBarItemHeight * i + SideBarItemHeight / 2;
            //画出侧边导航栏
            canvas.drawText(SIDEBAR[i], xPos, yPos, mSideBarMenuPaint);
            //重置画笔,如果重置画笔，则必须重新设置画笔属性
            //mSideBarMenuPaint.reset();
        }
    }


    /**
     * 手动处理触摸事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //获取触摸事件：ACTION_DOWN、ACTION_MOVE、ACTION_UP
        int action = event.getAction();
        //获取触摸的Y坐标
        float yPos = event.getY();
        //判断点击的是那一个item。计算公式：触摸的Y坐标/控件高度*侧边导航栏item总数。
        int pos = (int) (yPos / getHeight() * SIDEBAR.length);
        //记录之前用户点击的item
        int oldClick = click;
        //处理触摸事件，up事件单独处理，其他（ACTION_DOWN、ACTION_MOVE）在default中去处理。
        switch (action) {
            case MotionEvent.ACTION_UP:
                //复位 click = -1;
                if (mTvTips != null) {
                    mTvTips.setVisibility(View.GONE);
                }
                invalidate();
                break;
            default:
                if (oldClick != pos) {
                    if (pos > 0 && pos < SIDEBAR.length) {
                        if (mTvTips != null) {
                            mTvTips.setText(SIDEBAR[pos]);
                            mTvTips.setVisibility(View.VISIBLE);
                        }
                        click = pos;
                        //通知View树重绘，主线程用：invalidate()，非主线程用：postInvalidate()；
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }


    public void setTextView(TextView tips)
    {
        this.mTvTips = tips;
    }

}
