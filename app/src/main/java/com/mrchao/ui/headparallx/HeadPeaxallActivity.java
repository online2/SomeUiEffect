package com.mrchao.ui.headparallx;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
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

public class HeadPeaxallActivity extends BaseActivity {


    @BindView(R.id.tl_view)
    TabLayout mTlView;
    @BindView(R.id.vp_view)
    NoScrollViewPager mVpView;
    @BindView(R.id.llTopTitle)
    LinearLayout llTopTitle;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.llcentent)
    LinearLayout llcentent;
    @BindView(R.id.root)
    CoordinatorLayout root;
    @BindView(R.id.toobar)
    Toolbar toobar;
    private String[] title = {"主页", "微博", "相册"};

    @Override
    public int getLayoutId() {
        return R.layout.ac_head_parallax;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init() {
        StatusBarUtil.setTranslucentForImageView(this, null);
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager());
        mVpView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTlView));
        mTlView.setupWithViewPager(mVpView);
        mVpView.setAdapter(adapter);
//        mVpView.setNestedScrollingEnabled(false);
        int statuBarHeight = getStatuBarHeight(mContext);
        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) llTopTitle.getLayoutParams();
        layoutParams.topMargin = statuBarHeight;
        llTopTitle.setLayoutParams(layoutParams);
        //利用toobar属性来占位，使得自定义的headBar能够停在头部
        CollapsingToolbarLayout.LayoutParams layoutParams2 = (CollapsingToolbarLayout.LayoutParams) toobar.getLayoutParams();
        layoutParams2.topMargin = statuBarHeight;
        toobar.setLayoutParams(layoutParams2);


        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appbarlayout.getTotalScrollRange());
                Log.e("=======>",percent+"");
                llTopTitle.setAlpha(percent);//0-1
                StatusBarUtil.setTranslucentForImageView(HeadPeaxallActivity.this, (int) (255 * percent), null);
                if (percent == 0) {//表示还能用滚动
                    mVpView.setNoScroll(false);

                } else if (percent == 1){//表示已经完全展开
                    mVpView.setNoScroll(false);

                } else {
                    mVpView.setNoScroll(true);
                }
            }
        });


    }

    public static int getStatuBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(identifier);


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
