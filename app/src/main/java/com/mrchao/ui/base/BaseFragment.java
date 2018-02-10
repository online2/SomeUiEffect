package com.mrchao.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by MrChao on 2018/2/10.
 * 用户Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        mActivity = getActivity();
        View inflate = inflater.inflate(getLayoutId(),container, false);
        ButterKnife.bind(this,inflate);
        init();
        return inflate;
    }

    /**
     * @return 布局id
     */
    public abstract int getLayoutId();

    public abstract void init();
}
