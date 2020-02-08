package com.docking.sticktop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.docking.sticktop.R;
import com.docking.sticktop.bean.CommBean;
import com.docking.sticktop.constant.ViewType;
import com.docking.sticktop.holder.CommHolder;
import com.docking.sticktop.holder.NewsHolder;
import com.docking.sticktop.holder.TextHolder;
import com.docking.sticktop.widget.ChildRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dukangkang
 * @date: 2020-02-04 10:53.
 * @description: todo ...
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<CommHolder> {

    private List<CommBean> mList = new ArrayList<>();

    private Context mContext;

    private Fragment mFragment;

    private NewsHolder mNewHolder;

    public MultiTypeAdapter(Context context, List<CommBean> list) {
        this.mContext = context;
        this.mList = list;
        if (mList != null) {
            Log.w("dkk", "MultiTypeAdapter mList " + mList.size());
        } else {
            Log.w("dkk", "MultiTypeAdapter mList = null");
        }
    }

    public MultiTypeAdapter(Context context, Fragment fragment, List<CommBean> list) {
        this.mContext = context;
        this.mFragment = fragment;
        this.mList = list;
        if (mList != null) {
            Log.w("dkk", "MultiTypeAdapter mList " + mList.size());
        } else {
            Log.w("dkk", "MultiTypeAdapter mList = null");
        }
    }

    public void replace(List<CommBean> list) {
        this.mList = list;
        notifyDataSetChanged();
        Log.w("dkk", "replace");
    }

    @NonNull
    @Override
    public CommHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.w("dkk", "onCreateViewHolder viewType = " + viewType);
        CommHolder commHolder = null;
        if(viewType == ViewType.TYPE_TEXT) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_text, viewGroup, false);
            commHolder = new TextHolder(view);
        } else if (viewType == ViewType.TYPE_NEWS){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_news, viewGroup, false);
            mNewHolder = new NewsHolder(view);
            commHolder = mNewHolder;
        } else {
            commHolder = new CommHolder(new TextView(viewGroup.getContext()));
        }
        return commHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommHolder viewHolder, int position) {
        CommBean commBean = mList.get(position);
//        Log.w("dkk", "onBindViewHolder viewHolder = " + viewHolder);
        if (viewHolder instanceof TextHolder) {
            viewHolder.bindData(commBean);
        } else if (viewHolder instanceof NewsHolder) {
            viewHolder.setFragment(mFragment);
            viewHolder.bindData(commBean);
        }
    }

    @Override
    public int getItemViewType(int position) {
//        Log.w("dkk", "getItemViewType mList " + mList);
        if (mList == null) {
            return position;
        }

        CommBean commBean = mList.get(position);
        if (commBean == null) {
            return position;
        }
        return commBean.viewType;
    }

    @Override
    public int getItemCount() {
//        Log.w("dkk", "getItemCount mList " + mList);
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    public ChildRecyclerView getCurrentChildRecyclerView() {
        if (mNewHolder == null) {
            return null;
        }

        return mNewHolder.getCurrentChildRecyclerView();
    }

    public ViewPager getCurrentViewPager() {
        if (mNewHolder == null) {
            return null;
        }
        return mNewHolder.getCurrentViewPager();
    }

}
