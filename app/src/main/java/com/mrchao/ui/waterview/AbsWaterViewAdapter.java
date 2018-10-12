package com.mrchao.ui.waterview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by  Linfc on 2018/10/10.
 * desc：水滴View适配器
 * 对于的布局只要继承这个即可
 */
public abstract class AbsWaterViewAdapter<T> {
    private Context mContext;
    private int mLayoutResId;
    private List<T> mData;
    private WaterView mWaterView;

    public AbsWaterViewAdapter(@LayoutRes int layoutResId, Context context, List<T> data) {
        mContext = context;
        if (layoutResId != -1) {
            mLayoutResId = layoutResId;
        }
        mData = data;
    }


    /**
     * 初始化item View
     *
     * @param viewGroup 对应WaterView
     * @return 返回当前Item View
     */
    protected View initLayoutView(ViewGroup viewGroup) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutResId, viewGroup, false);
        mWaterView = (WaterView) viewGroup;
        return view;
    }

    /**
     * @param view     对应item View
     * @param t        对应的item 实体类数据
     * @param position 当前item View所处位置
     */
    protected abstract void convert(View view, T t, int position);

    /**
     * 防止WaterView中getData出现空指针
     *
     * @return
     */
    protected List<T> getData() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        return mData;
    }

    /**
     * @return 使当前适配器可以操作WaterView内部一些方法
     */
    public WaterView getWaterView() {
        return mWaterView;
    }

    public <T extends View> T getView(@NonNull View view, @IdRes int viewId) {
        if (view != null) {
            view = view.findViewById(viewId);
        }
        return (T) view;
    }

}
