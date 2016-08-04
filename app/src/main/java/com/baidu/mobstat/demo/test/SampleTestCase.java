/**
 * Copyright (C) 2014 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.mobstat.demo.test;

import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;

import com.baidu.mobstat.demo.DemoActivity1;
import com.baidu.mobstat.demo.DemoActivity2;
import com.baidu.mobstat.demo.DemoActivity3;
import com.baidu.mobstat.demo.WebViewActivity;
import com.robotium.solo.Solo;

public class SampleTestCase extends ActivityInstrumentationTestCase2<DemoActivity1> {

    /**
     * @param activityClass
     */
    public SampleTestCase() {
        super(DemoActivity1.class);
    }

    private Solo mSolo;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        mSolo = new Solo(getInstrumentation(), getActivity());
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.test.ActivityInstrumentationTestCase2#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        mSolo.finishOpenedActivities();
    }

    public void testActivity() {

        mSolo.clickOnButton("切换到下一个Activity");
        mSolo.waitForActivity(DemoActivity2.class);

        mSolo.clickOnButton("切换到下一个Activity");
        mSolo.waitForActivity(DemoActivity3.class);

        mSolo.goBack();
        mSolo.waitForActivity(DemoActivity2.class);

        mSolo.goBack();
        mSolo.waitForActivity(DemoActivity1.class);

        mSolo.clickOnButton("自定义事件");

        mSolo.clickOnButton("切换到WebView");
        mSolo.waitForActivity(WebViewActivity.class);

        SystemClock.sleep(10000);
    }
}
