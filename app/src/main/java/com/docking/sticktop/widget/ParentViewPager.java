package com.docking.sticktop.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ParentViewPager extends ViewPager {
    // true: 允许滑动， false:禁用滑动
    private boolean enableScroll = true;

    public ParentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentViewPager(Context context) {
        super(context);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        // View没有置顶的情况下，禁用子View左右滑动事件
        Log.w("dkk", "ParentViewPager canScroll = " + enableScroll);
        if (!enableScroll) {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    public void setEnableScroll(boolean scroll) {
        enableScroll = scroll;
    }
}