package com.docking.sticktop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.docking.sticktop.adapter.MainAdapter;
import com.docking.sticktop.event.TopEvent;
import com.docking.sticktop.listener.OnChildScrollLisener;
import com.docking.sticktop.widget.ParentViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dukangkang
 * @date: 2020-02-04 11:06.
 * @description: todo ...
 */
public class MainActivity extends AppCompatActivity implements OnChildScrollLisener, View.OnClickListener, ViewPager.OnPageChangeListener {

    private int mPosition = 0;

    private List<String> mList = new ArrayList<>();
    private ParentViewPager mViewPager = null;

    private MainAdapter mAdapter = null;

    private RelativeLayout mNewsTitleRylt;
    private ImageView mNewsBackIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        init();
        initListener();

    }

    private void init() {
        mList.add("北京");
        mList.add("上海");
        mList.add("天津");

        mAdapter = new MainAdapter(this.getSupportFragmentManager());
        mAdapter.replace(mList);

        mViewPager = this.findViewById(R.id.main_comm_viewpager);
        mViewPager.setAdapter(mAdapter);

        this.findViewById(R.id.main_comm_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击一次", Toast.LENGTH_SHORT).show();
            }
        });

        mNewsTitleRylt = this.findViewById(R.id.newstitle_rlyt);
        mNewsBackIv = this.findViewById(R.id.newstitle_title_back);
    }

    private void initListener() {
        mAdapter.setOnChildScrollLisener(this);
        mViewPager.addOnPageChangeListener(this);
        // 禁用事件透传
        mNewsTitleRylt.setOnClickListener(null);
        mNewsBackIv.setOnClickListener(this);
    }

    private void test() {

        int coreNum = DeviceInfoManager.getCPUCoreNum();
        Log.w("dkk", "coreNum = " + coreNum);

        long availableMemory = DeviceInfoManager.getAvailableMemory(this)/1024;
        Log.w("dkk", "availableMemory = " + availableMemory);

        long totalMemory = DeviceInfoManager.getTotalMemory();
        Log.w("dkk", "totalMemory = " + totalMemory);


        String appMemory = DeviceInfoManager.getAppMemory();
        Log.w("dkk", "appMemory = " + appMemory);

        long appCpuTime = DeviceInfoManager.getAppCpuTime();
        Log.w("dkk", "appCpuTime = " + appCpuTime);

        String curCpuFreq = DeviceInfoManager.getCurCpuFreq();
        Log.w("dkk", "curCpuFreq = " + curCpuFreq);

        String minCpuFreq = DeviceInfoManager.getMinCpuFreq();
        Log.w("dkk", "minCpuFreq = " + minCpuFreq);

        String maxCpuFreq = DeviceInfoManager.getMaxCpuFreq();
        Log.w("dkk", "maxCpuFreq = " + maxCpuFreq);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TopEvent event) {
        Log.w("dkk", "TPGActivity TopEvent event.isTop = " + event.isTop);
        mViewPager.setEnableScroll(!event.isTop);
        // recyclerview 快速滑动到顶端，直接修改背景色
        if (event.isTop) {
            mNewsTitleRylt.setAlpha(1);
            mNewsTitleRylt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onScroll(float alpha) {
        mNewsTitleRylt.setAlpha(alpha);
    }

    @Override
    public void onVisible(boolean visible) {
        if (visible) {
            mNewsTitleRylt.setVisibility(View.VISIBLE);
        } else {
            mNewsTitleRylt.setAlpha(0);
            mNewsTitleRylt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mNewsBackIv.getId()) {
            if (mAdapter != null) {
                mAdapter.reset(mPosition);
            }
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
//        Log.w("dkk", "--> onPageScrolled i = " + i + " v = " + v);
    }

    @Override
    public void onPageSelected(int position) {
        Log.w("dkk", "--> onPageSelected position = " + position);
        this.mPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        Log.w("dkk", "--> onPageScrollStateChanged state = " + state);
    }
}
