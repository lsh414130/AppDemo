package com.example.jeremylau.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jeremylau.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeremylau on 2018/7/19.
 */
public class AddItemOrViewActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.back_image)
    ImageView backImage;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ll_addView)
    LinearLayout llAddView;
    @BindView(R.id.btn_getData)
    Button btnGetData;
    private String TAG = this.getClass().getSimpleName();
    //装在所有动态添加的Item的LinearLayout容器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        ButterKnife.bind(this);
        btnGetData.setOnClickListener(this);
        rlBack.setOnClickListener(this);
        //默认添加一个Item
        addViewItem(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addHotel://点击添加按钮就动态添加Item
                addViewItem(v);
                break;
            case R.id.btn_getData://打印数据
                printData();
                break;
            case R.id.rl_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * Item排序
     */
    private void sortHotelViewItem() {
        //获取LinearLayout里面所有的view
        for (int i = 0; i < llAddView.getChildCount(); i++) {
            final View childAt = llAddView.getChildAt(i);
            final Button btn_remove = (Button) childAt.findViewById(R.id.btn_addHotel);
            btn_remove.setText("删除");
            btn_remove.setTag("remove");//设置删除标记
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //从LinearLayout容器中删除当前点击到的ViewItem
                    llAddView.removeView(childAt);
                }
            });
            //如果是最后一个ViewItem，就设置为添加
            if (i == (llAddView.getChildCount() - 1)) {
                Button btn_add = (Button) childAt.findViewById(R.id.btn_addHotel);
                btn_add.setText("+新增");
                btn_add.setTag("add");
                btn_add.setOnClickListener(this);
            }
        }
    }

    //添加ViewItem
    private void addViewItem(View view) {
        if (llAddView.getChildCount() == 0) {//如果一个都没有，就添加一个
            View hotelEvaluateView = View.inflate(this, R.layout.item_hotel_evaluate, null);
            Button btn_add = (Button) hotelEvaluateView.findViewById(R.id.btn_addHotel);
            btn_add.setText("+新增");
            btn_add.setTag("add");
            btn_add.setOnClickListener(this);
            llAddView.addView(hotelEvaluateView);
            //sortHotelViewItem();
        } else if (((String) view.getTag()).equals("add")) {//如果有一个以上的Item,点击为添加的Item则添加
            View hotelEvaluateView = View.inflate(this, R.layout.item_hotel_evaluate, null);
            llAddView.addView(hotelEvaluateView);
            sortHotelViewItem();
        }
        //else {
        //  sortHotelViewItem();
        //}
    }

    //获取所有动态添加的Item，找到控件的id，获取数据
    private void printData() {
        for (int i = 0; i < llAddView.getChildCount(); i++) {
            View childAt = llAddView.getChildAt(i);
            EditText hotelName = (EditText) childAt.findViewById(R.id.ed_hotelName);
            RatingBar hotelEvaluateStart = (RatingBar) childAt.findViewById(R.id.rb_hotel_evaluate);
            EditText hotelEvaluate = (EditText) childAt.findViewById(R.id.ed_hotelEvaluate);
            Log.e(TAG, "酒店名称：" + hotelName.getText().toString() + "-----评价星数："
                    + (int) hotelEvaluateStart.getRating() + "-----服务评价：" + hotelEvaluate.getText().toString());
        }
    }


    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                hideSoftInput(view);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    // 隐藏软键盘
    private void hideSoftInput(View view) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}