package com.mrchao.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mrchao.ui.base.BaseActivity;
import com.mrchao.ui.coordinatorlayout.CoordinatorActivity;
import com.mrchao.ui.custombehavior.CustomBehaviorActivity;
import com.mrchao.ui.lbeguide.LBEEffectActivity;

import java.util.ArrayList;

import butterknife.BindView;


public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_view)
    RecyclerView mRvView;
    private ArrayList<String> listItem = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initData();
        initView();
    }

    private void initData() {
        listItem.add("平行空间引导页效果");
        listItem.add("CoordinatorLayout效果学习");
        listItem.add("自定义Behavior");
}

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRvView.setLayoutManager(manager);
        MainAdapter adapter = new MainAdapter(listItem);
        mRvView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentJunmp(position);
            }
        });
    }

    private void IntentJunmp(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(mActivity, LBEEffectActivity.class);
                break;
            case 1:
                intent = new Intent(mActivity, CoordinatorActivity.class);
                break;
            case 2:
                intent = new Intent(mActivity, CustomBehaviorActivity.class);
                break;
        }
        startActivity(intent);
    }

}
