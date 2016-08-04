package com.baidu.mobstat.demo;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobstat.StatService;
import com.baidu.mobstat.demo.R;

public class DemoActivity2 extends Activity {
    /** Called when the activity is first created. */

    private Button btnPre;
    private Button btnNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(Conf.TAG, "DemoActivity2.OnCreate()");
        setContentView(R.layout.layout2);

        ActivityManager am = (ActivityManager) getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            System.out.println(info.id);
        }

        btnPre = (Button) findViewById(R.id.layout2_btn1);
        btnNext = (Button) findViewById(R.id.layout2_btn2);

        btnPre.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DemoActivity2.this, DemoActivity1.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DemoActivity2.this, DemoActivity3.class);
                startActivity(intent);
            }
        });
    }

    public void onResume() {
        Log.w(Conf.TAG, "Activity2.OnResume()");
        super.onResume();

        StatWrapper.onResume(this);
    }

    public void onPause() {
        Log.w(Conf.TAG, "Activity2.onPause()");
        super.onPause();

        StatWrapper.onPause(this);
    }
}

class StatWrapper {
    public static void onResume(Context context) {

        /**
         * 此处调用基本统计代码
         */
        StatService.onResume(context);
    }

    public static void onPause(Context context) {

        /**
         * 此处调用基本统计代码
         */
        StatService.onPause(context);
    }
}