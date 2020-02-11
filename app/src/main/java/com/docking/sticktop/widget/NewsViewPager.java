package com.docking.sticktop.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author: dukangkang
 * @date: 2020-02-10 14:37.
 * @description: todo ...
 */
public class NewsViewPager extends ViewPager {

    private boolean enalbeIntercept = false;
    public NewsViewPager(@NonNull Context context) {
        super(context);
    }

    public NewsViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    float curY = 0;
//    float lastY = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        curY = ev.getY();
//        Log.w("dkk", "NewsViewPager + curY = " + ev.getY());
//        Log.w("dkk", "NewsViewPager + lastY = " + lastY);
        Log.e("dkk", "NewsViewPager dispatchTouchEvent");

//        lastY = curY;

//        if (enalbeIntercept) {
//            this.getParent().requestDisallowInterceptTouchEvent(true);
//        } else {
//            this.getParent().requestDisallowInterceptTouchEvent(false);
//        }
        return super.dispatchTouchEvent(ev);
    }

    public void setEnalbeIntercept(boolean enalbeIntercept) {
        this.enalbeIntercept = enalbeIntercept;
    }
}
