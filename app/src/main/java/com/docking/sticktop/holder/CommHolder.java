package com.docking.sticktop.holder;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author: dukangkang
 * @date: 2020-02-04 10:58.
 * @description: todo ...
 */
public class CommHolder<T> extends RecyclerView.ViewHolder {

    public CommHolder(@NonNull View itemView) {
        super(itemView);
    }

    public CommHolder(@NonNull View itemView, Fragment fragment) {
        super(itemView);
    }

    public void setFragment(Fragment fragment) {

    }

    public void bindData(T bean) {

    }
}
