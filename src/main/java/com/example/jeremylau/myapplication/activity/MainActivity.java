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

    @BindView(R.id.test_tv)
    Button testTv;
    @BindView(R.id.test_tv2)
    Button testTv2;
    @BindView(R.id.test_tv3)
    Button testTv3;
    @BindView(R.id.test_tv4)
    Button testTv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test_tv, R.id.test_tv2, R.id.test_tv3,R.id.test_tv4})
    public void selectOnClick(View v) {
        switch (v.getId()) {
            case R.id.test_tv:
                startActivity(new Intent(this, SiderBarMenuActivity.class));
                break;
            case R.id.test_tv2:
                startActivity(new Intent(this, LauncherActivity.class));
                break;
            case R.id.test_tv3:
                startActivity(new Intent(this, H5Activity.class));
                break;
            case R.id.test_tv4:
                startActivity(new Intent(this, ReadingH5Activity.class));
                break;
            default:
                break;
        }
    }


}
