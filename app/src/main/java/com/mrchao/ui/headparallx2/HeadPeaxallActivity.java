package com.mrchao.ui.headparallx2;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.mrchao.ui.R;
import com.mrchao.ui.base.BaseActivity;
import com.mrchao.ui.headparallx.CoordinatorFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MrChao on 2018/2/10.
 * 仿照微博首页吸顶折叠效果
 */

public class HeadPeaxallActivity extends BaseActivity {


    @BindView(R.id.tl_view)
    TabLayout mTlView;
    @BindView(R.id.vp_view)
    ViewPager mVpView;
    @BindView(R.id.headScrollableLayout)
    HeaderViewPager headScrollableLayout;
    @BindView(R.id.ivover)
    ImageView ivover;
    private String[] title = {"主页", "微博", "相册"};
    private ArrayList<Fragment> mdate = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.ac_head_parallax_2;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init() {
        StatusBarUtil.setTranslucentForImageView(this, null);

        mdate.add(com.mrchao.ui.headparallx2.CoordinatorFragment.getInstance("主页"));
        mdate.add(com.mrchao.ui.headparallx2.CoordinatorFragment.getInstance("weibo"));
        mdate.add(com.mrchao.ui.headparallx2.CoordinatorFragment.getInstance("xiangce"));

        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager());
        mVpView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTlView));
        mTlView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                headScrollableLayout.setCurrentScrollableContainer((HeaderScrollHelper.ScrollableContainer)mdate.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTlView.setupWithViewPager(mVpView);
        mVpView.setAdapter(adapter);
        headScrollableLayout.setCurrentScrollableContainer((HeaderScrollHelper.ScrollableContainer) mdate.get(0));

        headScrollableLayout.setParallaxImageView(ivover);

        headScrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
                Log.e("==============>",currentY + "===" + maxY);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mdate.get(position);
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
