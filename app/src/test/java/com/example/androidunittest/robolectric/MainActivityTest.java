package com.example.androidunittest.robolectric;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.androidunittest.BuildConfig;
import com.example.androidunittest.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;

/**
 * Robolectric的简单使用：(目前Robolectric最高支持sdk版本为23)
 * Robolectric实现一套JVM能运行的Android代码，从而做到脱离Android运行环境进行测试
 * Created by 陈健宇 at 2019/4/8
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mMainActivity;

    @Before
    public void setUp() throws Exception {
        //单元测试中无法输出Log信息。这时我们可以使用ShadowLog
        ShadowLog.stream = System.out;
        //Robolectric.setupActivity返回的时候，默认会调用Activity的生命周期: onCreate -> onStart -> onResume。
        mMainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    //测试跳转到下一个Activity
    @Test
    public void startWelcomeActivityTest(){
        View btnClick = mMainActivity.findViewById(R.id.btn_click);
        // 触发按钮点击
        btnClick.performClick();
        //获取MainActivity的Shadow类,并通过Shadow类获取启动下一个Activity的Intent
        ShadowActivity shadowActivity = Shadows.shadowOf(mMainActivity);
        Intent nextIntent = shadowActivity.getNextStartedActivity();
        //期待的Intent
        Intent expectedIntent = new Intent(mMainActivity, WelcomeActivity.class);
        // 校验Intent的正确性
        Assert.assertEquals("Intent不相等", expectedIntent.getComponent().getClassName(), nextIntent.getComponent().getClassName());
    }

    //测试Toast
    @Test
    public void toastTest(){

        //使用ShadowToast类获取最新展示的Toast
        Toast toast = ShadowToast.getLatestToast();
        //判断Toast尚未弹出
        Assert.assertNull(toast);

        mMainActivity.findViewById(R.id.btn_toast).performClick();

        toast = ShadowToast.getLatestToast();
        //判断Toast已经弹出
        Assert.assertNotNull(toast);
        //通过ShadowToast简单工厂类获取Toast中的文本
        Assert.assertEquals("Hello!", ShadowToast.getTextOfLatestToast());

    }

}