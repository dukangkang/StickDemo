package com.docking.sticktop.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.docking.sticktop.R;
import com.docking.sticktop.bean.CustomEntity;


public class CustomHolder extends RecyclerView.ViewHolder {

    TextView mTitleTv;
    ImageView mIconIv;
    public CustomHolder(@NonNull View itemView) {
        super(itemView);
        this.mTitleTv = itemView.findViewById(R.id.custom_item_title);
        this.mIconIv = itemView.findViewById(R.id.custom_item_image);
    }

    public void initData(CustomEntity customEntity) {
        if (customEntity == null) {
            return;
        }

        mTitleTv.setText(customEntity.title);
//        Glide.with(mTitleTv.getContext()).load(customEntity.url).into(mIconIv);
    }
}
