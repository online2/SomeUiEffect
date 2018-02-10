package com.mrchao.ui.coordinatorlayout;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mrchao.ui.R;
import com.mrchao.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MrChao on 2018/2/10.
 * CoorinatorLayout效果
 * ViewPager+TabLayout+Fragment + AppBarLayout
 * 仿照微博首页吸顶折叠效果
 */

public class CoordinatorActivity extends BaseActivity {


    @BindView(R.id.tl_view)
    TabLayout mTlView;
    @BindView(R.id.vp_view)
    ViewPager mVpView;
    private String[] title = {"主页", "微博", "相册"};

    @Override
    public int getLayoutId() {
        return R.layout.ac_coordinator;
    }

    @Override
    public void init() {
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager());
        mVpView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTlView));
        mTlView.setupWithViewPager(mVpView);
        mVpView.setAdapter(adapter);
    }



    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CoordinatorFragment.getInstance(title[position]);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
