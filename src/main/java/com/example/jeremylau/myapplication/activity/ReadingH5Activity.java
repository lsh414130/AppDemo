package com.example.jeremylau.myapplication.activity;




import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jeremylau.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by jeremylau on 2018/7/16.
 */
public class ReadingH5Activity extends AppCompatActivity {
    public static final String TAG = "ReadingH5Activity";

    @BindView(R.id.back_image)
    ImageView backImage;
    @BindView(R.id.reading_back)
    RelativeLayout readingBack;
    @BindView(R.id.reading_title)
    TextView readingTitle;
    @BindView(R.id.reading_WebView)
    WebView mWebView;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        readingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //加载本地asset下面的test.html文件
        mWebView.loadUrl("file:///android_asset/reading.html");
        //加载网页
        //mWebView.loadUrl("https://www.baidu.com");
        //在APP中打开需要设置setWebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //android调用html页面中的JS方法
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//打开js支持

        mWebView.addJavascriptInterface(new JsReading(), "android");
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());


    }

    /**
     * 自己写一个类，里面是提供给H5访问的方法
     */
    public class JsReading {

        @JavascriptInterface//一定要写，不然H5调不到这个方法
        public String book() {
            return "[{ book_name: '《书名书名1》', book_writer: '著者：某某某1', begin_time: '2017-07-12', limit_time: '2017-07-12', name: '姓名：某某某1', student_id: '学号：123456', card_id: '证号：123456' },{ book_name: '《书名书名2》', book_writer: '著者：某某某2', begin_time: '2017-07-12', limit_time: '2017-07-12', name: '姓名：某某某2', student_id: '学号：123456', card_id: '证号：123456' },{ book_name: '《书名书名3》', book_writer: '著者：某某某3', begin_time: '2017-07-12', limit_time: '2017-07-12', name: '姓名：某某某3', student_id: '学号：123456', card_id: '证号：123456' }]";
        }
    }

}
