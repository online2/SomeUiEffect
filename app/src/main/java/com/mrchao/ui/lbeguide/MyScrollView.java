package com.mrchao.ui.lbeguide;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
public class MyScrollView extends HorizontalScrollView {
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 返回发false直接把在事件分发的时候直接拦截了，
     * 这样做可以使得当滑动位置是在MyScrollView区域时，
     * 事件也不会响应直接交给外层的Viewpage处理，使得滑动更细腻
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }
/**
 * 而用这下面两个当滑动位置是在MyScrollView区域时事件会被拦截
 */
//    	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		return true;
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		return true;
//	}

}
