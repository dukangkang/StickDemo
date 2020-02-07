package com.docking.sticktop.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

import com.docking.sticktop.adapter.MultiTypeAdapter;
import com.docking.sticktop.bean.CommBean;
import com.docking.sticktop.bean.TextBean;
import com.docking.sticktop.constant.ViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dukangkang
 * @date: 2020-02-05 11:02.
 * @description: todo ...
 */
public class CategoryView extends ChildRecyclerView {

    private List<CommBean> mDataList = new ArrayList<>();

    private Context mContext = null;
    private MultiTypeAdapter mAdapter;

    public CategoryView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CategoryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CategoryView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        Log.w("dkk", "CategoryView init");
        this.mContext = context;
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
//        setLayoutParams(new LayoutParams(400, 800));

        Log.w("dkk", "CategoryView initRecyclerView");
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new MultiTypeAdapter(mContext, mDataList);
        setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            TextBean textBean = new TextBean();
            textBean.title = "default child item " + i;
            textBean.viewType = ViewType.TYPE_TEXT;
            mDataList.add(textBean);
        }
//        mAdapter.notifyDataSetChanged();
    }

}
