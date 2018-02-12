package com.mrchao.ui.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrchao.ui.R;
import com.mrchao.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MrChao on 2018/2/12.
 * 属性动画效果实现练习
 */

public class AnimatorActivity extends BaseActivity {
    @BindView(R.id.tv_change)
    TextView mTvChange;
    @BindView(R.id.one)
    LinearLayout mOne;
    @BindView(R.id.two)
    ImageView mTwo;

    @Override
    public int getLayoutId() {
        return R.layout.ac_animator;
    }

    @Override
    public void init() {

    }


    @OnClick({R.id.tv_change, R.id.two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                startAnima(mOne, mTwo, true);
                break;
            case R.id.two:
                startAnima(mOne, mTwo, false);
                break;
        }
    }

    private void startAnima(View fistView, final View second, final boolean isOpen) {
        float startRota = 0f, endRota = 25f;
        float startalpha = 1f, endalpha = 1f;
        float startScale = 1f, endScale = 1f;
        float startY = 0f, endY = 0f;
        float startY2 = 0f, endY2 = 0f;
        /**
         * 打开效果
         * 1:翻转动画 先翻转，在摆正
         * 2:透明度 1- 0.5
         * 3:缩放
         * 4::位移
         */
        if (isOpen) {
            startScale = 1f;
            endScale = 0.8f;
            startalpha = 1f;
            endalpha = 0.5f;
            startY = 0f;
            endY = -0.1f * fistView.getHeight();
            startY2 = second.getHeight();
            endY2 = -0f;
        } else {
            startScale = 0.8f;
            endScale = 1f;
            endalpha = 1f;
            startalpha = 0.5f;
            endY = 0f;
            startY = -0.1f * fistView.getHeight();
            endY2 = second.getHeight();
            startY2 = -0f;
        }
        //翻转
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(fistView, "rotationX", startRota, endRota);
        rotationAnim.setDuration(300);
        //透明度
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(fistView, "alpha", startalpha, endalpha);
        alphaAnim.setDuration(300);
        //缩放
        ObjectAnimator scaleAnimX = ObjectAnimator.ofFloat(fistView, "scaleX", startScale, endScale);
        scaleAnimX.setDuration(300);
        ObjectAnimator scaleAnimY = ObjectAnimator.ofFloat(fistView, "scaleY", startScale, endScale);
        scaleAnimY.setDuration(300);
        //在翻转摆正
        ObjectAnimator rotationAnimR = ObjectAnimator.ofFloat(fistView, "rotationX", endRota, startRota);
        rotationAnimR.setDuration(300);
        //平移，因为缩放了所以，平移缩放后产生的距离
        ObjectAnimator tranlationAnim = ObjectAnimator.ofFloat(fistView, "translationY", startY, endY);
        tranlationAnim.setDuration(300);
        tranlationAnim.setStartDelay(300);
        //第二个View平移
        ObjectAnimator tranlationAnim2 = ObjectAnimator.ofFloat(second, "translationY", startY2, endY2);
        tranlationAnim2.setDuration(300);
        tranlationAnim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                second.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mTvChange.setClickable(!isOpen);
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotationAnim, alphaAnim, scaleAnimX, scaleAnimY, rotationAnimR, tranlationAnim, tranlationAnim2);
        set.start();
    }
}
