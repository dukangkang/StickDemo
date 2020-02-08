package com.docking.sticktop.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.docking.sticktop.widget.ParentRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class WeatherFragment extends Fragment implements View.OnClickListener {

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
    }

    /**
     * 置顶
     * @param isTop
     */
    private void stickTop(final boolean isTop) {
        mAdapter.notifyTop(isTop);
    }

    /**
     * 设置是否展开
     */
    private void gotoTopAndBottom(final boolean isExpanded) {

    }

    @Override
    public void onClick(View v) {
    }

}
