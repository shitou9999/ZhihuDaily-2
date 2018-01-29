package com.github.jokar.zhihudaily.di.module.activity

import android.support.v4.app.Fragment
import com.github.jokar.zhihudaily.di.subComponent.MainFragmentSubComponent
import com.github.jokar.zhihudaily.di.subComponent.ThemeFragmentSubComponent
import com.github.jokar.zhihudaily.ui.fragment.MainFragment
import com.github.jokar.zhihudaily.ui.fragment.ThemeFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

/**
 * @Qualifier: 当类的类型不足以鉴别一个依赖的时候，我们就可以使用这个注解标示。
 * 例如：在Android中，我们会需要不同类型的context，所以我们就可以定义 qualifier注解’@ForApplication’和“@ForActivity”，
 * 这样当注入一个context的时候，我们就可以告诉 Dagger我们想要哪种类型的context。
 * @Module 带有此注解的类，用来提供依赖，里面定义一些用@Provides注解的以provide开头的方法，
 * 这些方法就是所提供的依赖，Dagger2会在该类中寻找实例化某个类所需要的依赖。
 */
@Module
abstract class FragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    internal abstract fun bindMainFragment(builder: MainFragmentSubComponent.Builder)
            : AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(ThemeFragment::class)
    internal abstract fun bindThemeFragment(builder: ThemeFragmentSubComponent.Builder)
            : AndroidInjector.Factory<out Fragment>
}