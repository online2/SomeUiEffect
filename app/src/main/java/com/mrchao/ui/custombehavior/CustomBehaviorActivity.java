package com.mrchao.ui.custombehavior;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mrchao.ui.MainAdapter;
import com.mrchao.ui.R;
import com.mrchao.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MrChao on 2018/2/11.
 * 自定义Behavior
 */

public class CustomBehaviorActivity extends BaseActivity {
//    @BindView(R.id.tv_observable)
//    TextView mTvObservable;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private ArrayList<String> listItem = new ArrayList<>();
    @Override
    public int getLayoutId() {
//        return R.layout.ac_custom_behavior1;
//        return R.layout.ac_custom_behavior2;
        return R.layout.ac_custom_behavior_fab;
    }

    @Override
    public void init() {
        initData();
        initRecycleView();

    }

    private void initData() {
        listItem.clear();
        for (int i = 0; i < 80; i++) {
            listItem.add("Item"+i);
        }
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerview.setLayoutManager(manager);
        MainAdapter adapter = new MainAdapter(listItem);
        mRecyclerview.setAdapter(adapter);
    }

//    @OnClick(R.id.tv_observable)
//    public void onViewClicked() {
//        ViewCompat.offsetTopAndBottom(mTvObservable, 10);
//    }



}
