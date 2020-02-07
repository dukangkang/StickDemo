package com.docking.sticktop.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import com.docking.sticktop.R;
import com.docking.sticktop.bean.TextBean;

/**
 * @author: dukangkang
 * @date: 2020-02-04 10:58.
 * @description: todo ...
 */
public class TextHolder extends CommHolder<TextBean> {

    private TextView textView;

    public TextHolder(@NonNull View itemView) {
        super(itemView);
        textView = this.itemView.findViewById(R.id.textView);
    }

    @Override
    public void bindData(TextBean textBean) {
        super.bindData(textBean);
        if (textBean != null) {
            textView.setText(textBean.title);
        }
    }
}
