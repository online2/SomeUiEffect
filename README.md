做这个项目原因，因为之前在做项目过程中都是零零碎碎的使用到这些东西，所以现在利用空闲的时间，<br>
把这些使用过的东西进行大整合，顺便复习回顾，巩固，加强记忆。<br>
* 1:添加平行空间引导页实现：<br> *
=================
    效果图: <br>
    ![image](https://github.com/online2/SomeUiEffect/blob/openMaster/images/lbeguide.gif) <br>
    博客地址:<br>
    http://blog.csdn.net/lfc18606951877/article/details/79291162 <br>
* 2:添加CoordinatorLayout+ViewPage+TabLayout实现微博个人中心页面效果 <br> *
=================
    ![image](https://github.com/online2/SomeUiEffect/blob/openMaster/images/stickynav.gif)<br>
* 3:自定义Behavior <br> *
=================
    结合CoordinatorLayou布局有时候需要如下两种情况<br>
———————————-
 *   1：某个View需要监听另外一个View的状态(比如：位置，大小，显示状态等)==》如项目中CustomBehaviorOne  <br> *
    (继承:CoordinatorLayout.Behavior，重写方法：layoutDependsOn和onDependentViewChanged) <br>

 *   2：某个View需要监听CoordinatorLayout里面所有控件的滑动状态===》如项目中CustomBehaviorTwo和FabBehavior   <br> *
    (继承:CoordinatorLayout.Behavior 重写onStartNestedScroll 和onNestedScroll或onNestedPreScroll） <br>
    注意：能被CoordinatorLayout捕获：RecyclerView/NestedScrollView/ViewPager， <br>
    其中FabBehavior结合了FloatingActionButton来呈现效果 <br>