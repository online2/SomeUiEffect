package com.mrchao.ui.headparallx2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mrchao.ui.MainAdapter;
import com.mrchao.ui.R;
import com.mrchao.ui.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by MrChao on 2018/2/10.
 * CoordinatorFragment页面
 */

public class CoordinatorFragment extends BaseFragment implements HeaderScrollHelper.ScrollableContainer {

    @BindView(R.id.rv_view)
    RecyclerView mRvView;
    private ArrayList<String> mList = new ArrayList<>();
    private String mTitle;

    public static CoordinatorFragment getInstance(String title) {
        CoordinatorFragment fragment = new CoordinatorFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_coordinator_fm;
    }

    @Override
    public void init() {
        mTitle = (String) getArguments().get("title");
        initData();
        initView();
    }

    private void initData() {
        mList.clear();
        int i, size = 40;
        for (i = 0; i < size; i++) {
            mList.add(mTitle);
        }
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRvView.setLayoutManager(manager);
        MainAdapter adapter = new MainAdapter(mList);
        mRvView.setAdapter(adapter);
    }

    @Override
    public View getScrollableView() {
        return mRvView;
    }
}
