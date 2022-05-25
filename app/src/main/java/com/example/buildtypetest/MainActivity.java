package com.example.buildtypetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.buildtypetest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        webview = binding.activityMainWebview;
        Log.d("##", "getApplicationID" + getAppId(this));
        Log.d("##", "getPackageName" + getPackageName(this));





        if (BuildConfig.IS_DEBUG && BuildConfig.IS_A == true) {
            Log.d("##", "getApplicationID" + getAppId(this));
            Log.d("##", "getPackageName" + getPackageName(this));
            webview.loadUrl("https://www.naver.com");
            webview.setWebContentsDebuggingEnabled(true);


        } else {
            webview.loadUrl("https://www.google.com");

        }
    }

    public static String getAppId(Context context) {
        String appId = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo i = pm.getPackageInfo(context.getPackageName(), 0);
            appId = i.applicationInfo.loadDescription(pm) + "";
        } catch(PackageManager.NameNotFoundException e) { }
        return appId;
    }

    public static String getPackageName(Context context) {
        String packageName = ""; // 패키지명 예시 데이터
        try {
            PackageManager packagemanager = context.getPackageManager();
            ApplicationInfo appinfo = packagemanager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            packageName = packagemanager.getApplicationLabel(appinfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }
}