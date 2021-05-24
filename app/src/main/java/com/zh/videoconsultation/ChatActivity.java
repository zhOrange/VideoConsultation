package com.zh.videoconsultation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";

    private WebView webView;
    private final String TARGETURL = "https://47.104.147.103:3000";
    private String[] permissionsAAA = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS};

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        PermissionUtils.checkAndRequestMorePermissions(ChatActivity.this, permissionsAAA, 1, new PermissionUtils.PermissionRequestSuccessCallBack() {
            @Override
            public void onHasPermission() {
                Log.d(TAG, "onHasPermission: get the permission of camera");
            }
        });

        webView = findViewById(R.id.webview_rtc);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(TARGETURL);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    Log.d("加载完成...","success");
                } else {
                    // 加载中
                    Log.d("加载中...",+newProgress+"");
                }
            }
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Log.d(TAG, "run: request the permission : " + request.getResources());
                request.grant(request.getResources());
            }// onPermissionRequest
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                //返回上一页面
                webView.goBack();
                return true;
            }
            else
            {
                //退出程序
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestMorePermissionsResult(ChatActivity.this, permissions, new PermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
//                toCamera();
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {
                Toast.makeText(ChatActivity.this, "我们需要"+ Arrays.toString(permission)+"权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                Toast.makeText(ChatActivity.this, "我们需要"+Arrays.toString(permission)+"权限", Toast.LENGTH_SHORT).show();
                // 显示前往设置页的dialog
//                showToAppSettingDialog();
            }
        });
    }
}