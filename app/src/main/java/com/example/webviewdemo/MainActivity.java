package com.example.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    // public WebView webView = null; // WebView 实例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置为全屏（隐藏状态栏）
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        this.createWebView();
    }

    /*
     * 创建 WebView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void createWebView() {
        // 创建 WebView 实例并通过 id 绑定我们刚创建的布局
        // 这里的 R.id.webview 就是 activity_main.xml 中的 WebView 标签的 id
        // this.webView = (WebView) findViewById(R.id.webview);
        final WebView webView = (WebView) findViewById(R.id.webview);
        // 设置 WebView 允许执行 JavaScript 脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 确保跳转到另一个网页时仍然在当前 WebView 中显示
        // 而不是调用浏览器打开
        webView.setWebViewClient(new WebViewClient());
        // 设置 WebView 的按键监听器，覆写监听器的 onKey 函数，对返回键作特殊处理
        // 当 WebView 可以返回到上一个页面时回到上一个页面
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });
        // 加载指定网页
        String url = "http://192.168.137.1:7456";
        webView.loadUrl(url);
    }

    /*
     * 接管返回键
     */
    /* @Override
     public void onBackPressed() {
         if (this.webView.canGoBack()) {
             this.webView.goBack();
         } else {
             super.onBackPressed();
         }
     }*/

}