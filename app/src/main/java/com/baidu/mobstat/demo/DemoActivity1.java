package com.baidu.mobstat.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mobstat.NativeCrashHandler;
import com.baidu.mobstat.SendStrategyEnum;
import com.baidu.mobstat.StatService;
//import com.baidu.mobstat.StatService.WearListener;
import com.baidu.mobstat.demo.appfragment.AppFragmentDemoActivity;
import com.baidu.mobstat.demo.supportv4fragment.MainFragmentActivity;
import com.baidu.mobstat.demo.R;

public class DemoActivity1 extends Activity {
    private Button btnPrev;
    private Button btnNext;
    private Button btnWebview;
    private Button btnException;
    private Button btnNativeException;
    private Button btnSetTV;
    private Button btnEvent;
    private Button btnEventDuration;
    private Button btnEventStart;
    private Button btnEventEnd;
    private Button btnFragmentPage;
    private Button btnAppFragmentPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout1);

        boolean isWear = getPackageManager().hasSystemFeature("android.hardware.type.watch");

        Log.e("TEST", "isWear: " + isWear);

        Log.e("TEAT", "manufacturer: " + Build.MANUFACTURER);

        // 设置AppKey
        // StatService.setAppKey("a9e2ad84a2"); // appkey必须在mtj网站上注册生成，该设置建议在AndroidManifest.xml中填写，代码设置容易丢失

        /*
         * 设置渠道的推荐方法。该方法同setAppChannel（String）， 如果第三个参数设置为true（防止渠道代码设置会丢失的情况），将会保存该渠道，每次设置都会更新保存的渠道，
         * 如果之前的版本使用了该函数设置渠道，而后来的版本需要AndroidManifest.xml设置渠道，那么需要将第二个参数设置为空字符串,并且第三个参数设置为false即可。
         * appChannel是应用的发布渠道，不需要在mtj网站上注册，直接填写就可以 该参数也可以设置在AndroidManifest.xml中
         */
        // StatService.setAppChannel(this, "RepleceWithYourChannel", true);
        // 测试时，可以使用1秒钟session过期，这样不断的间隔1S启动退出会产生大量日志。
        StatService.setSessionTimeOut(30);
        // setOn也可以在AndroidManifest.xml文件中填写，BaiduMobAd_EXCEPTION_LOG，打开崩溃错误收集，默认是关闭的
        StatService.setOn(this, StatService.EXCEPTION_LOG);
        /*
         * 设置启动时日志发送延时的秒数<br/> 单位为秒，大小为0s到30s之间<br/> 注：请在StatService.setSendLogStrategy之前调用，否则设置不起作用
         * 
         * 如果设置的是发送策略是启动时发送，那么这个参数就会在发送前检查您设置的这个参数，表示延迟多少S发送。<br/> 这个参数的设置暂时只支持代码加入，
         * 在您的首个启动的Activity中的onCreate函数中使用就可以。<br/>
         */
        StatService.setLogSenderDelayed(0);
        /*
         * 用于设置日志发送策略<br /> 嵌入位置：Activity的onCreate()函数中 <br />
         * 
         * 调用方式：StatService.setSendLogStrategy(this,SendStrategyEnum. SET_TIME_INTERVAL, 1, false); 第二个参数可选：
         * SendStrategyEnum.APP_START SendStrategyEnum.ONCE_A_DAY SendStrategyEnum.SET_TIME_INTERVAL 第三个参数：
         * 这个参数在第二个参数选择SendStrategyEnum.SET_TIME_INTERVAL时生效、 取值。为1-24之间的整数,即1<=rtime_interval<=24，以小时为单位 第四个参数：
         * 表示是否仅支持wifi下日志发送，若为true，表示仅在wifi环境下发送日志；若为false，表示可以在任何联网环境下发送日志
         */
        StatService.setSendLogStrategy(this, SendStrategyEnum.SET_TIME_INTERVAL, 1, false);
        // 调试百度统计SDK的Log开关，可以在Eclipse中看到sdk打印的日志，发布时去除调用，或者设置为false
        StatService.setDebugOn(true);

        String sdkVersion = StatService.getSdkVersion();

        // StatService.setWearListener(new WearListener() {
        //
        // @Override
        // public boolean onSendLogData(String data) {
        // Log.e("TEST", "data: " + data);
        //
        // return true;
        // }
        // });

        TextView sdkVersionTxtv = (TextView) findViewById(R.id.tv_sdk_version);
        if (sdkVersion != null) {
            sdkVersionTxtv.setText("sdk version is: " + sdkVersion);
        }

        btnPrev = (Button) findViewById(R.id.layout1_btn1);
        btnNext = (Button) findViewById(R.id.layout1_btn2);
        btnWebview = (Button) findViewById(R.id.layout1_btn_web_view);

        btnException = (Button) findViewById(R.id.layout1_btn_excep);
        btnNativeException = (Button) findViewById(R.id.layout1_btn_native_excep);
        btnNativeException.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!MyApplication.SHIELD_EXCEPTION) {
                    NativeCrashHandler.doNativeCrash();
                }
            }
        });

        btnSetTV = (Button) findViewById(R.id.layout1_btn_set_TV);
        btnSetTV.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                StatService.setForTv(DemoActivity1.this, true);
            }
        });

        btnEvent = (Button) findViewById(R.id.layout1_btn_event);
        btnEventDuration = (Button) findViewById(R.id.layout1_btn_event_duration);

        btnEventStart = (Button) findViewById(R.id.layout1_btn_event_start);
        btnEventEnd = (Button) findViewById(R.id.layout1_btn_event_end);

        btnFragmentPage = (Button) findViewById(R.id.layout1_fragment);

        btnAppFragmentPage = (Button) findViewById(R.id.layout1_app_fragment);

        findViewById(R.id.btn_another_process).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoActivity1.this, AnotherDemoActivity1.class);
                startActivity(intent);
            }
        });

        btnPrev.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DemoActivity1.this, DemoActivity3.class);

                DemoActivity1.this.startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DemoActivity1.this, DemoActivity2.class);
                DemoActivity1.this.startActivity(intent);
            }
        });

        btnWebview.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DemoActivity1.this, WebViewActivity.class);
                DemoActivity1.this.startActivity(intent);
            }
        });

        btnException.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        if (!MyApplication.SHIELD_EXCEPTION) {
                            Log.w(Conf.TAG, 10 / 0 + "");
                        }
                    }
                };
                t.start();
            }
        });

        btnEvent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // "registered id"必须在mtj网站的自定义事件中添加， “pass”是该注册事件下的事件
                // new Thread(new Runnable(){
                // @Override
                // public void run(){
                // StatService.onEvent(DemoActivity1.this.getApplicationContext(), "registered id",
                // "pass", 1);
                // }
                // }).run();

                StatService.onEvent(DemoActivity1.this.getApplicationContext(), "registered id", "pass", 1);
            }
        });

        /**
         * 自定义事件的第一种方法，写入某个事件的持续时长
         */
        btnEventDuration.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // 事件id（"registered id"）的事件pass，其时长持续100毫秒
                StatService.onEventDuration(DemoActivity1.this, "registered id", "pass", 100);

            }
        });

        /*
         * 自定义事件的第二种方法，自己定义该事件的起始时间和结束时间
         */
        btnEventStart.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // 事件id（"registered id"）的事件pass，其时长持续10毫秒
                StatService.onEventStart(DemoActivity1.this, "registered id", "pass"); // 必须和onEventEnd共用才行

            }
        });

        /*
         * 自定义事件的第二种方法，自己定义该事件的起始时间和结束时间
         */
        btnEventEnd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // 事件id（"registered id"）的事件pass，其时长持续10毫秒
                StatService.onEventEnd(DemoActivity1.this, "registered id", "pass"); // 必须和onEventStart共用才行

            }
        });

        btnFragmentPage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(DemoActivity1.this, MainFragmentActivity.class);
                startActivity(in);
            }
        });

        btnAppFragmentPage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    Intent in = new Intent();
                    in.setClass(DemoActivity1.this, AppFragmentDemoActivity.class);
                    startActivity(in);
                }
            }
        });

    }

    public void onResume() {
        Log.w(Conf.TAG, "Activity1.OnResume()");
        super.onResume();

        /**
         * 页面起始（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
         * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
         */
        StatService.onResume(this);
    }

    public void onPause() {
        Log.w(Conf.TAG, "Activity1.onPause()");
        super.onPause();

        /**
         * 页面结束（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
         * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
         */
        StatService.onPause(this);
    }
}
// /~
