package com.github.jokar.zhihudaily.di.module.activity

import android.app.Activity
import com.github.jokar.zhihudaily.di.subComponent.CollectionSubComponent
import com.github.jokar.zhihudaily.di.subComponent.MainSubComponent
import com.github.jokar.zhihudaily.di.subComponent.SettingSubComponent
import com.github.jokar.zhihudaily.di.subComponent.StoryDetailSubComponent
import com.github.jokar.zhihudaily.ui.activity.CollectionActivity
import com.github.jokar.zhihudaily.ui.activity.MainActivity
import com.github.jokar.zhihudaily.ui.activity.SettingActivity
import com.github.jokar.zhihudaily.ui.activity.StoryDetailActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * @Module: Modules类里面的方法专门提供依赖，所以我们定义一个类，用@Module注解，
 * 这样Dagger在构造类的实例的时候，就知道从哪里去找到需要的依赖。
 * modules的一个重要特征是它们设计为分区并组合在一起（比如说，在我们的app中可以有多个组成在一起的modules）
 * @PerActivity 注解一个类，所以这个对象存活时间就和 activity的一样。
 * 简单来说就是我们可以定义所有范围的粒度(@PerFragment, @PerUser, 等等)。
 */
@Module
abstract class ActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(CollectionActivity::class)
    internal abstract fun bindCollectionActivity(builder: CollectionSubComponent.Builder): AndroidInjector.Factory<out Activity>


    @Binds
    @IntoMap
    @ActivityKey(SettingActivity::class)
    internal abstract fun bindSettingActivity(builder: SettingSubComponent.Builder): AndroidInjector.Factory<out Activity>


    @Binds
    @IntoMap
    @ActivityKey(StoryDetailActivity::class)
    internal abstract fun bindStoryDetailActivity(builder: StoryDetailSubComponent.Builder): AndroidInjector.Factory<out Activity>
}