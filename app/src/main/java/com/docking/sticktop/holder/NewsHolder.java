package com.docking.sticktop.holder;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.docking.sticktop.R;
import com.docking.sticktop.adapter.NewsPagerAdapter;
import com.docking.sticktop.bean.NewsBean;
import com.docking.sticktop.widget.ChildRecyclerView;

/**
 * @author: dukangkang
 * @date: 2020-02-04 11:00.
 * @description: todo ...
 */
public class NewsHolder extends CommHolder<NewsBean> {

    private Fragment mFragment = null;

    private TabLayout mTabLayout = null;
    private ViewPager mViewPager = null;

    private NewsPagerAdapter mNewsPagerAdapter;

//    private List<ChildRecyclerView> viewList = new ArrayList<>();

    private ChildRecyclerView mCurrentRecyclerView = null;


    public NewsHolder(@NonNull View itemView) {
        super(itemView);
        mTabLayout = itemView.findViewById(R.id.news_tabs);
        mViewPager = itemView.findViewById(R.id.news_viewpager);
        initListener();
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if(viewList != null && !viewList.isEmpty()) {
//                    mCurrentRecyclerView = viewList.get(position);
//                    Log.d("dkk","onPageSelected: position = " + position + " mCurrentRecyclerView = " + mCurrentRecyclerView);
//                }

                if (mNewsPagerAdapter != null) {
                    mCurrentRecyclerView = mNewsPagerAdapter.getChildRecyclerView(position);
                    Log.d("dkk","onPageSelected: position = " + position + " mCurrentRecyclerView = " + mCurrentRecyclerView);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void bindData(NewsBean newsBean) {
        super.bindData(newsBean);
        if (newsBean == null) {
            return;
        }

        if (newsBean.tabList == null) {
            return;
        }

        int currentItem = mViewPager.getCurrentItem();

        int size = newsBean.tabList.size();
        Log.w("dkk", "size = " + size);

        mNewsPagerAdapter = new NewsPagerAdapter(mFragment.getChildFragmentManager());
        mNewsPagerAdapter.replace(newsBean.tabList);

        mViewPager.setAdapter(mNewsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(currentItem);

        mCurrentRecyclerView = mNewsPagerAdapter.getChildRecyclerView(currentItem);
    }

    public ViewPager getCurrentViewPager() {
        return mViewPager;
    }

    public ChildRecyclerView getCurrentChildRecyclerView() {
        return mCurrentRecyclerView;
    }
}
