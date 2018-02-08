package com.mrchao.ui;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mrchao.ui.base.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by MrChao on 2018/2/8.
 * 首页界面适配器
 */

public class MainAdapter extends BaseRecycleViewAdapter<String> {
    public MainAdapter(@Nullable List<String> data) {
        super(R.layout.ac_lbe_item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);

    }
}
