package com.github.jokar.zhihudaily.di.module

import android.content.Context
import com.github.jokar.zhihudaily.app.MyApplication
import com.github.jokar.zhihudaily.di.subComponent.*
import dagger.Module
import dagger.Provides


/**
 * @Modules 组成该组件，如果缺失了任何一块都会在编译的时候报错。
 * 所有的组件都可以通过它的modules知道依赖的范围
 */
@Module(subcomponents = arrayOf(MainSubComponent::class,
        MainFragmentSubComponent::class,
        StoryDetailSubComponent::class,
        ThemeFragmentSubComponent::class,
        CollectionSubComponent::class,
        SettingSubComponent::class))
class AppModule {
    /**
     * 提供全局的context
     */
    @Provides
    fun contextProvider(application: MyApplication): Context {
        return application.applicationContext
    }
}