package com.docking.sticktop.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.docking.sticktop.bean.TabBean;
import com.docking.sticktop.event.TopEvent;
import com.docking.sticktop.fragment.CustomFragment;
import com.docking.sticktop.widget.ChildRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private List<TabBean> mList;
    private List<Fragment> mFramentList = new ArrayList<>();


    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.w("dkk", "NewsPagerAdapter getItem position = " + position);
        CustomFragment fragment = new CustomFragment();
        mFramentList.add(position, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void replace(List<TabBean> list) {
        if (mList != null) {
            mList.clear();
        }
        this.mList = list;
        notifyDataSetChanged();
    }

    public Fragment getCurFragment(int position) {
        if (mFramentList == null || mFramentList.isEmpty()) {
            return null;
        }
        int size = mFramentList.size();
        if (size <= position) {
            return null;
        }
        return mFramentList.get(position);
    }

    public ChildRecyclerView getChildRecyclerView(int position) {
        ChildRecyclerView childRecyclerView = null;
        Fragment curFragment = getCurFragment(position);
        if (curFragment instanceof CustomFragment) {
            childRecyclerView = ((CustomFragment) curFragment).getRecyclerView();
        }
        return childRecyclerView;
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }

//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
//    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mList == null) {
            return "";
        }
        TabBean tabBean = mList.get(position);
        if (tabBean == null) {
            return "";
        }
        return tabBean.title;
    }

    public void notifyTop(boolean isTop) {
        EventBus.getDefault().post(new TopEvent(isTop));
    }
}
