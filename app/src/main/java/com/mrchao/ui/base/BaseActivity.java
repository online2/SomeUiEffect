package com.mrchao.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by MrChao on 2018/2/7.
 * 基类
 */

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    /**
     *
     * @return 布局id
     */
    public abstract int getLayoutId();
}
