package com.mrchao.ui.custombehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by MrChao on 2018/2/11.
 * 自定义Behavior
 * (一) 某个View需要监听另外一个View的状态(比如：位置，大小，显示状态等)
 *      View之间相互监听
 */

public class CustomBehaviorOne extends CoordinatorLayout.Behavior<View> {
    public CustomBehaviorOne() {
    }

    public CustomBehaviorOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param parent     父容器
     * @param child      子控件-->需要监听dependency这个View的视图  既类似观察者
     * @param dependency 你要监听的那个View
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //还可以根据ID 或者tag去判断区分具体View
        return dependency instanceof TextView || super.layoutDependsOn(parent, child, dependency);
    }

    /**
     * 当被监听的View发生改变的时候回调
     * 可以在此方法里面做一些响应的联动动画等效果
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //获取被监听View的状态---比如：垂直方向位置
        int offset = dependency.getTop() - child.getTop();
        //让child平移
        ViewCompat.offsetTopAndBottom(child,offset);

        return super.onDependentViewChanged(parent, child, dependency);

    }
}
