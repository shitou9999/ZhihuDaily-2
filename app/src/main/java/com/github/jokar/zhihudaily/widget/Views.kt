package com.github.jokar.zhihudaily.widget

import android.view.ViewManager
import com.github.jokar.zhihudaily.widget.viewpager.AutoScrollViewPager
import com.github.jokar.zhihudaily.widget.viewpager.MotorTrackerViewPager
import com.like.LikeButton
import com.rd.PageIndicatorView
import org.jetbrains.anko.custom.ankoView

/**
 * 高级用法 - 如何使用自定义view
 * 怎么把自己的自定义控件使用到anko中呢
 * 1.首先创建一个Views.kt文件
 * 2.在该文件中添加下面代码
 * inline fun ViewManager.customizeView(theme: Int = 0) = customizeView(theme) {}
 * inline fun ViewManager.customizeView(theme: Int = 0, init: CustomizeView.() -> Unit) = ankoView({ CustomizeView(it) }, theme, init)
 *  这里的```CustomizeView```是你的自定义View
 *  这里的```customizeView```是你用在anko里的名称，一般就是自定义view的名称，首字母小写这样的写法
 * 3.然后就可以在anko里这样使用了customizeView{}
 */
inline fun ViewManager.likeButton(theme: Int = 0) = likeButton(theme) {}

inline fun ViewManager.likeButton(theme: Int = 0, init: LikeButton.() -> Unit) = ankoView({ LikeButton(it) }, theme, init)

//CircleImageView
inline fun ViewManager.circleImageView(theme: Int = 0) = circleImageView(theme) {}

inline fun ViewManager.circleImageView(theme: Int = 0, init: CircleImageView.() -> Unit) = ankoView({ CircleImageView(it) }, theme, init)

//PageIndicatorView
inline fun ViewManager.pageIndicatorView(theme: Int = 0) = pageIndicatorView(theme) {}

inline fun ViewManager.pageIndicatorView(theme: Int = 0, init: PageIndicatorView.() -> Unit) = ankoView({ PageIndicatorView(it) }, theme, init)

//MotorTrackerViewPager
inline fun ViewManager.motorTrackerViewPager(theme: Int = 0) = motorTrackerViewPager(theme) {}

inline fun ViewManager.motorTrackerViewPager(theme: Int = 0, init: MotorTrackerViewPager.() -> Unit) = ankoView({ MotorTrackerViewPager(it) }, theme, init)

//VerticalRecyclerView
inline fun ViewManager.verticalRecyclerView(theme: Int = 0) = verticalRecyclerView(theme) {}

inline fun ViewManager.verticalRecyclerView(theme: Int = 0, init: VerticalRecyclerView.() -> Unit) = ankoView({ VerticalRecyclerView(it) }, theme, init)

//LoadLayout
inline fun ViewManager.loadLayout(theme: Int = 0) = loadLayout(theme) {}

inline fun ViewManager.loadLayout(theme: Int = 0, init: LoadLayout.() -> Unit) = ankoView({ LoadLayout(it) }, theme, init)

//autoScrollViewPager
inline fun ViewManager.autoScrollViewPager(theme: Int = 0) = autoScrollViewPager(theme) {}

inline fun ViewManager.autoScrollViewPager(theme: Int = 0, init: AutoScrollViewPager.() -> Unit) = ankoView({ AutoScrollViewPager(it) }, theme, init)
