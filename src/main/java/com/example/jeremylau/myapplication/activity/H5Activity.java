package com.example.jeremylau.myapplication.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeremylau.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeremylau on 2018/7/17.
 */
public class H5Activity extends AppCompatActivity {

    public static final String TAG = "H5Activity";

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
        setContentView(R.layout.activity_web);
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
        mWebView.loadUrl("file:///android_asset/test.html");
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
        /**
         * 打开js接口給H5调用，参数1为本地类名，参数2为别名；h5用window.别名.类名里的方法名才能调用方法里面的内容，例如：window.android.back();
         * */
        mWebView.addJavascriptInterface(new JsInteration(), "android");
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());


    }


    /**
     * 自己写一个类，里面是提供给H5访问的方法
     * */
    public class JsInteration {

        @JavascriptInterface//一定要写，不然H5调不到这个方法
        public String back() {
            return "我是java里的方法返回值";
        }
    }

    //点击按钮，访问H5里带返回值的方法
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {
        Log.e("TAG", "onClick: ");

//        mWebView.loadUrl("JavaScript:show()");//直接访问H5里不带返回值的方法，show()为H5里的方法


        //传固定字符串可以直接用单引号括起来
        mWebView.loadUrl("javascript:alertMessage('哈哈')");//访问H5里带参数的方法，alertMessage(message)为H5里的方法

        //当出入变量名时，需要用转义符隔开
        String content="9880";
        mWebView.loadUrl("javascript:alertMessage(\""+content+"\")");


        //Android调用有返回值js方法，安卓4.4以上才能用这个方法
        mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.d(TAG, "js返回的结果为=" + value);
                Toast.makeText(H5Activity.this,"js返回的结果为=" + value,Toast.LENGTH_LONG).show();
            }
        });
    }
}