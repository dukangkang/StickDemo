package com.docking.sticktop.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.docking.sticktop.ChangeListener;
import com.docking.sticktop.R;
import com.docking.sticktop.adapter.MultiTypeAdapter;
import com.docking.sticktop.adapter.NewsPagerAdapter;
import com.docking.sticktop.bean.CommBean;
import com.docking.sticktop.bean.NewsBean;
import com.docking.sticktop.bean.TabBean;
import com.docking.sticktop.bean.TextBean;
import com.docking.sticktop.constant.ViewType;
import com.docking.sticktop.event.TopEvent;
import com.docking.sticktop.listener.OnChildScrollLisener;
import com.docking.sticktop.widget.ParentRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    private int changeHeight = 200;

    private String[] strArray = {"关注", "关注", "关注", "关注", "关注", "关注"};

    private List<CommBean> mDataList = new ArrayList<>();

    private SmartRefreshLayout mSmartRefreshLayout = null;

    private ParentRecyclerView mParentRecyclerView;
    private MultiTypeAdapter mMultiTypeAdapter;

    private NewsPagerAdapter mAdapter = null;

    private int position = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        position = bundle.getInt("KEY_POSITION");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();
        initListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.weather_fragment, null);
        return view;
    }

    private void init() {
        mSmartRefreshLayout = getView().findViewById(R.id.weather_smartrefresh_layout);
        mParentRecyclerView = getView().findViewById(R.id.weather_recyclerview);
        mParentRecyclerView.initLayoutManager(this.getContext());
    }

    private void initData() {
        mMultiTypeAdapter = new MultiTypeAdapter(this.getContext(), this, mDataList);
        mParentRecyclerView.setAdapter(mMultiTypeAdapter);

        for (int i = 0; i < 10; i++) {
            TextBean textBean = new TextBean();
            textBean.title = "parent item text "+i;
            textBean.viewType = ViewType.TYPE_TEXT;
            mDataList.add(textBean);
        }

        NewsBean newsBean = new NewsBean();
        newsBean.viewType = ViewType.TYPE_NEWS;
        int len = strArray.length;
        for (int i = 0; i < len; i++) {
            TabBean tabBean = new TabBean();
            tabBean.title = strArray[i];
            newsBean.tabList.add(tabBean);
        }
        mDataList.add(newsBean);

        mMultiTypeAdapter.replace(mDataList);
    }

    private void initListener() {
        mParentRecyclerView.setChangeListener(new ChangeListener() {
            @Override
            public void onStateChanged(State state) {
                super.onStateChanged(state);
                if (state == State.EXPANDED) {
                    Log.w("dkk", "==> 展开");
                    EventBus.getDefault().post(new TopEvent(false));
                } else if (state == State.COLLAPSED) {
                    Log.w("dkk", "==> 折叠");
                    mSmartRefreshLayout.setEnableRefresh(false);
                    EventBus.getDefault().post(new TopEvent(true));
                }
            }
        });

        mParentRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 第一个可见Item的位置
                int position = layoutManager.findFirstVisibleItemPosition();
                int targetPos = position + 1;
                if (targetPos >= mMultiTypeAdapter.getItemCount()) {
                    return;
                }

                int viewType = mMultiTypeAdapter.getItemViewType(targetPos);
                if (viewType == ViewType.TYPE_NEWS) {
                    // 注意此操作如果第一项划出屏幕外,拿到的是空的，所以必须是position是0的时候才能调用
                    View firstView = layoutManager.findViewByPosition(targetPos);
                    // 第一项Item的高度
//                    int firstHeight = firstView.getHeight();
                    // 距离顶部的距离，是负数，也就是说-top就是它向上滑动的距离
                    int scrollY = firstView.getTop();
//                    // 要在它滑到二分之一的时候去渐变
//                    int changeHeight = 300;
                    Log.w("dkk", "==> scrollY = " + scrollY);
                    if (scrollY < changeHeight) {
                        float alpha = (float)(changeHeight - scrollY) / changeHeight;
//                        Log.w("dkk", "==> alpha = " + alpha);
//                        EventBus.getDefault().post(new ScrollEvent(alpha));
                        if (mChildScrollLisener != null) {
                            mChildScrollLisener.onVisible(true);
                            mChildScrollLisener.onScroll(alpha);
                        }
                    } else {
                        if (mChildScrollLisener != null) {
                            mChildScrollLisener.onVisible(false);
                        }
                    }
                } else {
                    if (mChildScrollLisener != null) {
                        mChildScrollLisener.onVisible(false);
                    }
                }
            }
        });
    }

//    /**
//     * 置顶
//     * @param isTop
//     */
//    private void stickTop(final boolean isTop) {
//        mAdapter.notifyTop(isTop);
//    }
//
//    /**
//     * 设置是否展开
//     */
//    private void gotoTopAndBottom(final boolean isExpanded) {
//
//    }

    @Override
    public void onClick(View v) {
    }

    private OnChildScrollLisener mChildScrollLisener = null;
    public void setOnChildScrollLisener(OnChildScrollLisener lisener) {
        this.mChildScrollLisener = lisener;
    }

    public void reset() {
        if (mParentRecyclerView != null) {
//            mParentRecyclerView.setEnableScroll(true);
            mParentRecyclerView.scrollToPosition(0);
        }
    }

}
