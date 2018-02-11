package com.mrchao.ui.custombehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MrChao on 2018/2/11.
 * 自定义Behavior
 * (一) 某个View需要监听另外一个View的状态(比如：位置，大小，显示状态等)
 * View之间相互监听
 */

public class CustomBehaviorTwo extends CoordinatorLayout.Behavior<View> {
    public CustomBehaviorTwo() {
    }

    public CustomBehaviorTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild,
                                       View target, int nestedScrollAxes) {
        // 当观察的View（RecyclerView）发生滑动的开始的时候回调的
        //nestedScrollAxes:滑动关联轴， 比如:我们现在只关心垂直的滑动。
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        // 当观察的view滑动的时候回调的
        int scrollY = target.getScrollY();
        child.setScrollY(scrollY);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        // 快速滑动惯性移动（松开手指后还会有滑动）
        ((NestedScrollView)child).fling((int) velocityY);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
}
