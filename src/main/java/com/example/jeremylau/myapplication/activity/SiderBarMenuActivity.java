package com.example.jeremylau.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jeremylau.myapplication.R;
import com.example.jeremylau.myapplication.widget.SiderBarMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeremylau on 2018/7/13.
 */
public class SiderBarMenuActivity extends AppCompatActivity {

    @BindView(R.id.sbm_siderbarmenu)
    SiderBarMenu sbmSiderbarmenu;
    @BindView(R.id.tv_tips)
    TextView tvTips;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siderbarmenu);
        ButterKnife.bind(this);
        sbmSiderbarmenu.setTextView(tvTips);
    }
}
