package com.mrchao.ui.headparallx;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Linfc on 2018/10/27.
 * <p>
 * 图片放大回弹,背景变色
 */
public class HeadParallxBehavior extends AppBarLayout.Behavior {
    private static final String TAG = "overScroll";
    private static final float TARGET_HEIGHT = 1500;
    private View mTargetView;
    private int mParentHeight;
    private int mTargetViewHeight;
    /**
     * 总共偏移量
     */
    private float mTotalDy;
    private float mLastScale;
    private int mLastBottom;
    private boolean isAnimate;
    /**
     * 是否正在回弹中
     */
    private boolean isRecovering = false;

    public HeadParallxBehavior() {
    }

    public HeadParallxBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        boolean handled = super.onLayoutChild(parent, abl, layoutDirection);
        // 需要在调用过super.onLayoutChild()方法之后获取
        if (mTargetView == null) {
            mTargetView = parent.findViewWithTag(TAG);
            if (mTargetView != null) {
                initial(abl);
            }
        }
        return handled;
    }

    private void initial(AppBarLayout abl) {
        //设置不裁剪子view，一定要设置否则无法出现视差效果
        abl.setClipChildren(false);
        mParentHeight = abl.getHeight();
        mTargetViewHeight = mTargetView.getHeight();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        isAnimate = true;
        //保证拉动头部时/和Tablayout 也能出现视差效果
        if (target instanceof DisInterceptNestedScrollView) return true;
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        //当达到临界值开始出现视差效果
        if (!isRecovering) {
            if (mTargetView != null && ((dy < 0 && child.getBottom() >= mParentHeight) || (dy > 0 && child.getBottom() > mParentHeight))) {
                scale(child, target, dy,consumed);
                return;
            }
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY) {
        if (velocityY > 500) {//当y速度>100,就秒弹回
            isAnimate = false;
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
        recovery(abl);
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }

    private void scale(AppBarLayout abl, View target, int dy,int[] consumed) {
        mTotalDy += -dy;
        mTotalDy = Math.min(mTotalDy, TARGET_HEIGHT);
        mLastScale = Math.max(1f, 1f + mTotalDy / TARGET_HEIGHT);
        ViewCompat.setScaleX(mTargetView, mLastScale);
        ViewCompat.setScaleY(mTargetView, mLastScale);
        mLastBottom = mParentHeight + (int) (mTargetViewHeight/2  * (mLastScale - 1));
        abl.setBottom(mLastBottom);
        target.setScrollY(0);
        //滑动了多少距离=mTotalDy  子view滑动了多少距离offset=(int) (mTargetViewHeight / 2 * (mLastScale - 1))，
        //需要把这个自 view滑动了多少距离赋值给consumed[1] 来告诉coordinatorlayout这样他就知道还只有处理这剩下的mTotalDy-offset 距离
        //如果不加这个，当头部处于拉伸状态，手指不抬起，底下recycleView会跟着滑动一段距离，appbarlayout才会折叠退出
        consumed[1] = (int) (mTargetViewHeight /2 * (mLastScale - 1));//
    }

    private void recovery(final AppBarLayout abl) {
        if (isRecovering) return;
        if (mTotalDy > 0) {
            isRecovering = true;
            mTotalDy = 0;
            if (isAnimate) {
                ValueAnimator anim = ValueAnimator.ofFloat(mLastScale, 1f).setDuration(200);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                            float value = (float) animation.getAnimatedValue();
                            ViewCompat.setScaleX(mTargetView, value);
                            ViewCompat.setScaleY(mTargetView, value);
                            int bottomOffest = (int) (mLastBottom - (mLastBottom - mParentHeight) * animation.getAnimatedFraction());
                            abl.setBottom(bottomOffest);
                        }
                    }
                );
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isRecovering = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                anim.start();
            } else {
                ViewCompat.setScaleX(mTargetView, 1f);
                ViewCompat.setScaleY(mTargetView, 1f);
                abl.setBottom(mParentHeight);
                isRecovering = false;
            }
        }
    }


}