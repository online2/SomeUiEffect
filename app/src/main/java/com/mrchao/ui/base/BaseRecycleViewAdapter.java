package com.mrchao.ui.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by MrChao on 2018/2/8.
 * 适配器基类抽取
 */

public abstract class BaseRecycleViewAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {
    public BaseRecycleViewAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseRecycleViewAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseRecycleViewAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }
}
