package com.mrchao.ui.lbeguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.mrchao.ui.R;
import com.mrchao.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by MrChao on 2018/2/7.
 * 平行空间引导页
 */

public class LBEEffectActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager mVp;

    private int[] layouts = {
            R.layout.ac_lbe_welcome1,
            R.layout.ac_lbe_welcome2,
            R.layout.ac_lbe_welcome3
    };

    @Override
    public int getLayoutId() {
        return R.layout.ac_lbe_effect;
    }

    @Override
    public void init() {
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(adapter);

        WelcompagerTransformer transformer = new WelcompagerTransformer();
        mVp.setPageTransformer(true, transformer);

        mVp.setOnPageChangeListener(transformer);
    }

    class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", layouts[position]);
            bundle.putInt("pageIndex", position);
            f.setArguments(bundle);
            return f;
        }

        @Override
        public int getCount() {
            return 3;
        }


    }
}
