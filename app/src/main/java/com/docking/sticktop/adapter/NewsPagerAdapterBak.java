//package com.docking.sticktop.adapter;
//
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.view.PagerAdapter;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import com.docking.sticktop.bean.TabBean;
//import com.docking.sticktop.widget.ChildRecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author: dukangkang
// * @date: 2020-02-04 20:05.
// * @description: todo ...x
// */
//public class NewsPagerAdapterBak extends PagerAdapter {
//    private List<ChildRecyclerView> mViewList = new ArrayList<>();
//    private List<TabBean> mTabTitleList = new ArrayList<>();
//
//    public NewsPagerAdapterBak(List<ChildRecyclerView> viewList, List<TabBean> tabTitleList) {
//        this.mViewList = viewList;
//        this.mTabTitleList = tabTitleList;
//        Log.w("dkk", "mViewList = " + mViewList.size());
//        Log.w("dkk", "mTabTitleList = " + mTabTitleList.size());
//    }
//
//    @Override
//    public int getCount() {
//        return mViewList.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object obj) {
//        return view == obj;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = mViewList.get(position);
//        Log.w("dkk", "view = " + view);
//        if (container == view.getParent()) {
//            container.removeView(view);
//        }
//        container.addView(view);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
////        super.destroyItem(container, position, object);
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        if (mTabTitleList == null) {
//            return "";
//        }
//        TabBean tabBean = mTabTitleList.get(position);
//        if (tabBean == null) {
//            return "";
//        }
//        return tabBean.title;
//    }
//
//}
