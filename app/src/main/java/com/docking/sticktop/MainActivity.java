package com.docking.sticktop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.docking.sticktop.adapter.MainAdapter;
import com.docking.sticktop.event.TopEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dukangkang
 * @date: 2020-02-04 11:06.
 * @description: todo ...
 */
public class MainActivity extends AppCompatActivity {

    private List<String> mList = new ArrayList<>();
    private ViewPager mViewPager = null;

    private MainAdapter mAdapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TopEvent event) {
        Log.w("dkk", "TPGActivity TopEvent event.isTop = " + event.isTop);
//        mViewPager.setScroll(event.isTop);
    }


}
