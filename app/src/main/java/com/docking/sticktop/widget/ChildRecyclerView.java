package com.docking.sticktop.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author: dukangkang
 * @date: 2020-02-04 10:29.
 * @description: todo ...
 */
public class ChildRecyclerView extends RecyclerView {

    // 标志是否置顶：true：未置顶，false:置顶,
    boolean flag = true;

    public ChildRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.w("dkk", "ChildRecyclerView isScrollTop() = " + isScrollTop());
        return super.dispatchTouchEvent(ev);
    }

//    /**
//     * 是否支持向下滚动
//     * @return
//     * 恢复状态，需要重置
//     */
//    public boolean isScrollTop() {
//        if (!flag) {
//            return flag;
//        }
//        //RecyclerView.canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
//        flag = !canScrollVertically(-1);
//        return flag;
//    }

    public boolean isScrollTop() {
        //RecyclerView.canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
        return !canScrollVertically(-1);
    }


}
