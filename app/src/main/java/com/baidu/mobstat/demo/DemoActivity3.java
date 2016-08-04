package com.baidu.mobstat.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobstat.StatService;
import com.baidu.mobstat.demo.R;

public class DemoActivity3 extends Activity {
	/** Called when the activity is first created. */

	private Button btn_pre;
	private Button btn_next;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.i(Conf.TAG, "DemoActivity3.OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout3);

		btn_pre = (Button) findViewById(R.id.layout3_btn1);
		btn_next = (Button) findViewById(R.id.layout3_btn2);

		btn_pre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoActivity3.this, DemoActivity2.class);
				startActivity(intent);
			}
		});
 
		btn_next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoActivity3.this, DemoDialogActivity.class);
				startActivity(intent);
			}
		});
	}

	public void onResume() {
		Log.w(Conf.TAG, "Activity3.OnResume()");
		super.onResume();

		/**
		 * 页面起始（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
//		StatService.onResume(this);
		StatService.onPageStart(this, "DemoActivity3p");
	}

	public void onPause() {
		Log.w(Conf.TAG, "Activity3.onPause()");
		super.onPause();

		/**
		 * 页面结束（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
//		StatService.onPause(this);
		StatService.onPageEnd(this, "DemoActivity3p");
	}
}