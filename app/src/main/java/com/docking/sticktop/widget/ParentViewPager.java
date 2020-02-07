package com.docking.sticktop.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ParentViewPager extends ViewPager {
    // true: 允许滑动， false:禁用滑动
    private boolean isScroll = false;

    public ParentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentViewPager(Context context) {
        super(context);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        // View没有置顶的情况下，禁用子View左右滑动事件
//        if (!isScroll) {
//            return false;
//        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    public void setScroll(boolean isTop) {
        // isTop true:置顶，false:未置顶
        isScroll = isTop;
    }
}