package com.docking.sticktop.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.docking.sticktop.ChangeListener;
import com.docking.sticktop.adapter.MultiTypeAdapter;

/**
 * @author: dukangkang
 * @date: 2020-02-04 10:09.
 * @description: todo ...
 */
public class ParentRecyclerView extends RecyclerView {

    private ChangeListener.State mCurrentState = ChangeListener.State.IDLE;


    public ParentRecyclerView(@NonNull Context context) {
        super(context);
    }

    public ParentRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void initLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context) {
            @Override
            public int scrollVerticallyBy(int dy, Recycler recycler, State state) {
                try {
                    return super.scrollVerticallyBy(dy, recycler, state);
                } catch (Exception e) {
                    return 0;
                }
            }

            @Override
            public void onLayoutChildren(Recycler recycler, State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean canScrollVertically() {
                ChildRecyclerView childRecyclerView = findNestedScrollingChildRecyclerView();
                if (childRecyclerView != null && !childRecyclerView.isScrollTop()) {
                    changeState(ChangeListener.State.COLLAPSED);
                    Log.v("dkk", "-->> childRecyclerView.isScrollTop = " + (childRecyclerView.isScrollTop()));
                } else {
                    Log.v("dkk", "-->> childRecyclerView = null");
//                    changeState(ChangeListener.State.EXPANDED);
                }
                return childRecyclerView == null || childRecyclerView.isScrollTop();
            }


            @Override
            public void addDisappearingView(View child) {
                try {
                    super.addDisappearingView(child);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }

        };
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(linearLayoutManager);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Log.e("dkk", "-->> dispatchTouchEvent ACTION_DOWN");
            stopScroll();
        }

        if(isScrollEnd()) {
//            Log.e("dkk", "-->> dispatchTouchEvent childRecyclerView");
            //如果父RecyclerView已经滑动到底部，需要让子RecyclerView滑动剩余的距离
            View childViewPager = findNestedScrollingChildViewPager();
            if (childViewPager != null) {
                changeState(ChangeListener.State.COLLAPSED);
                return childViewPager.dispatchTouchEvent(ev);
            }
        }

        changeState(ChangeListener.State.EXPANDED);

        try {
            boolean flag = super.dispatchTouchEvent(ev);
//            Log.e("dkk", "-->> dispatchTouchEvent flag = " + flag);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 切换
     * @param expanded
     */
    private void changeState(ChangeListener.State expanded) {
        if (mCurrentState != expanded) {
            if (mChangeListener != null) {
                mChangeListener.onStateChanged(expanded);
            }
            mCurrentState = expanded;
        }
    }

    private boolean isScrollEnd() {
        //RecyclerView.canScrollVertically(1)的值表示是否能向上滚动，false表示已经滚动到底部
        return !canScrollVertically(1);
    }

    private ViewPager findNestedScrollingChildViewPager() {
        if (getAdapter() instanceof MultiTypeAdapter) {
            MultiTypeAdapter adapter = (MultiTypeAdapter) getAdapter();
            return adapter.getCurrentViewPager();
        }
        return null;
    }

    private ChildRecyclerView findNestedScrollingChildRecyclerView() {
        if (getAdapter() instanceof MultiTypeAdapter) {
            MultiTypeAdapter adapter = (MultiTypeAdapter) getAdapter();
            return adapter.getCurrentChildRecyclerView();
        }
        return null;
    }

    private ChangeListener mChangeListener = null;
    public void setChangeListener(ChangeListener changeListener) {
        mChangeListener = changeListener;
    }

}
