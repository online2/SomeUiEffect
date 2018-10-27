package com.mrchao.ui.discrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class DiscrollView extends ScrollView {

	private DiscrollViewContent mContent;

	public DiscrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		View content = getChildAt(0);
		mContent = (DiscrollViewContent)content;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		View first = mContent.getChildAt(0);
		first.getLayoutParams().height = getHeight();
	}


	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);

		int scrollViewHeight = getHeight();
		//监听滑动----接口---->控制DiscrollViewContent的属性
		for(int i=0;i<mContent.getChildCount();i++){//遍历MyLinearLayout里面所有子控件(MyViewGroup)
			View child = mContent.getChildAt(i);
			if(!(child instanceof DiscrollvableInterface)){
				continue;
			}

			//ratio:0~1
			DiscrollvableInterface discrollvableInterface =  (DiscrollvableInterface) child;
			//1.child离scrollview顶部的高度 a
			int discrollvableTop = child.getTop();
			int discrollvableHeight = child.getHeight();

			//2.得到scrollview滑出去的高度  b  就是int t,
			//3.得到child离屏幕顶部的高度  c
			int discrollvableAbsoluteTop = discrollvableTop - t;
			//什么时候执行动画？当child滑进屏幕的时候
			if(discrollvableAbsoluteTop<=scrollViewHeight){
				int visibleGap = scrollViewHeight - discrollvableAbsoluteTop;
				//确保ratio是在0~1，超过了1 也设置为1
				discrollvableInterface.onDiscrollve(clamp(visibleGap/(float)discrollvableHeight, 1f,0f));
			}else{//否则，就恢复到原来的位置
				discrollvableInterface.onResetDiscrollve();
			}
		}
	}

	public static float clamp(float value, float max, float min){
		return Math.max(Math.min(value, max), min);
	}

}
