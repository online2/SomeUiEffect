package com.mrchao.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by MrChao on 2018/2/7.
 * 基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Activity mActivity;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mActivity = this;
        mContext = this;
        init();
    }

    /**
     * @return 布局id
     */
    public abstract int getLayoutId();

    public abstract void init();
}
