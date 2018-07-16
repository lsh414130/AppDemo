package com.example.jeremylau.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.jeremylau.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.test_tv)
    TextView testTv;
    @BindView(R.id.test_tv2)
    TextView testTv2;
//    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText("update thread");
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }

    @OnClick({R.id.test_tv,R.id.test_tv2})
    public void selectOnClick(View v) {
        switch (v.getId()) {
            case R.id.test_tv:
                startActivity(new Intent(this, SiderBarMenuActivity.class));
                break;
            case R.id.test_tv2:
                startActivity(new Intent(this, LauncherActivity.class));
                break;
            default:
                break;
        }
    }


}
