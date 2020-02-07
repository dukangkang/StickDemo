package com.docking.sticktop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.docking.sticktop.R;
import com.docking.sticktop.adapter.CustomAdapter;
import com.docking.sticktop.bean.CustomEntity;
import com.docking.sticktop.event.ScrollEvent;
import com.docking.sticktop.event.TopEvent;
import com.docking.sticktop.widget.ChildRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CustomFragment extends Fragment {

    private ChildRecyclerView mRecyclerView;
    private CustomAdapter mCustomAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_fragment, null, false);
        return view;
    }

    private void init() {
        mRefreshLayout = getView().findViewById(R.id.smart_refresh_layout);
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.w("dkk", "customFragment initRecyclerView");
        List<CustomEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CustomEntity entity = new CustomEntity();
            entity.title = "标题 " + i;
            entity.url = "http://dmimg.5054399.com/allimg/pkm/pk/22.jpg";
            list.add(entity);
        }
        mCustomAdapter = new CustomAdapter(getContext(), list);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = getView().findViewById(R.id.custom_recyclerview);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mCustomAdapter);
    }

    private void initListener() {
    }

    public ChildRecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TopEvent event) {
        Log.w("dkk", "CustomFragment TopEvent event.isTop = " + event.isTop);
//        mRefreshLayout.setScroll(event.isTop);
//        mRecyclerView.setScroll(event.isTop);
//        if (mRefreshLayout != null) {
//            mRefreshLayout.setEnableRefresh(event.isTop);
//        }
//        layoutManager.setCanScroll(event.isTop);
        mRecyclerView.stopScroll();
        mRecyclerView.scrollToPosition(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ScrollEvent event) {
        Log.w("dkk", "CustomFragment ScrollEvent.isScroll = " + event.isScroll);
    }
}
