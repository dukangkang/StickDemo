package com.docking.sticktop.adapter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.docking.sticktop.fragment.WeatherFragment;
import com.docking.sticktop.listener.OnChildScrollLisener;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentStatePagerAdapter implements OnChildScrollLisener {
    private List<String> mList;
    private List<WeatherFragment> mFragmentList = new ArrayList<>();

    private Fragment mCurrentPrimaryItem = null;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_POSITION", position);
        fragment.setArguments(bundle);
        fragment.setOnChildScrollLisener(this);

        mFragmentList.add(position, fragment);
        Log.w("dkk", "====> position = " + position + " fragment = " + fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void replace(List<String> list) {
        if (mList != null) {
            mList.clear();
        }
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onScroll(float alpha) {
        Log.w("dkk", "onScroll alpha = " + alpha);
        if (mChildScrollLisener != null) {
            mChildScrollLisener.onScroll(alpha);
        }
    }

    @Override
    public void onVisible(boolean visible) {
        Log.w("dkk", "onScroll visible = " + visible);
        if (mChildScrollLisener != null) {
            mChildScrollLisener.onVisible(visible);
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        mFragmentList.set(position, null);
    }

    public WeatherFragment getCurFragment(int position) {
        if (mFragmentList == null) {
            return null;
        }
        return mFragmentList.get(position);
    }

    public void reset(int position) {
        if (mCurrentPrimaryItem == null) {
            return;
        }

        if (mCurrentPrimaryItem instanceof WeatherFragment) {
            ((WeatherFragment)mCurrentPrimaryItem).reset();
        }
    }

    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = (Fragment)object;
        if (fragment != this.mCurrentPrimaryItem) {
            if (this.mCurrentPrimaryItem != null) {
                this.mCurrentPrimaryItem.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }

            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            this.mCurrentPrimaryItem = fragment;
        }

    }


    private OnChildScrollLisener mChildScrollLisener = null;
    public void setOnChildScrollLisener(OnChildScrollLisener lisener) {
        this.mChildScrollLisener = lisener;
    }
}
