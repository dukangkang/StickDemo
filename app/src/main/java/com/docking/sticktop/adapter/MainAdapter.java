package com.docking.sticktop.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.docking.sticktop.fragment.WeatherFragment;

import java.util.List;

public class MainAdapter extends FragmentStatePagerAdapter {
    private List<String> mList;
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_POSITION", position);
        fragment.setArguments(bundle);

//        CustomFragment fragment = new CustomFragment();
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
}
