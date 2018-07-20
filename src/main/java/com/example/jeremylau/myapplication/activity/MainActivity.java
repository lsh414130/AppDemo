package com.example.jeremylau.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jeremylau.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {


    @BindView(R.id.test_bt)
    Button testBt;
    @BindView(R.id.test_bt2)
    Button testBt2;
    @BindView(R.id.test_bt3)
    Button testBt3;
    @BindView(R.id.test_bt4)
    Button testBt4;
    @BindView(R.id.test_bt5)
    Button testBt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test_bt, R.id.test_bt2, R.id.test_bt3, R.id.test_bt4,R.id.test_bt5})
    public void selectOnClick(View v) {
        switch (v.getId()) {
            case R.id.test_bt:
                startActivity(new Intent(this, SiderBarMenuActivity.class));
                break;
            case R.id.test_bt2:
                startActivity(new Intent(this, LauncherActivity.class));
                break;
            case R.id.test_bt3:
                startActivity(new Intent(this, H5Activity.class));
                break;
            case R.id.test_bt4:
                startActivity(new Intent(this, ReadingH5Activity.class));
                break;
            case R.id.test_bt5:
                startActivity(new Intent(this, AddItemOrViewActivity.class));
                break;
            default:
                break;
        }
    }


}
