package com.mrchao.ui.discrollview;

public interface DiscrollvableInterface {


	/**
	 * 当滑动的时候调用该方法，用来控制里面的控件执行相应的动画
	 * @param ratio
	 */
	 void onDiscrollve(float ratio);

	/**
	 * 重置view的属性----恢复view的原来属性
	 */
	 void onResetDiscrollve();

}
