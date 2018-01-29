package com.github.jokar.zhihudaily.di.component

import android.app.Application
import com.github.jokar.zhihudaily.app.MyApplication
import com.github.jokar.zhihudaily.di.module.AppModule
import com.github.jokar.zhihudaily.di.module.activity.ActivityModule
import com.github.jokar.zhihudaily.di.module.activity.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


/**
 * @Component: Components从根本上来说就是一个注入器，也可以说是@Inject和@Module的桥梁，它的主要作用就是连接这两个部分
 * Components可以提供所有定义了的类型的实例，比如：我们必须用@Component注解一个接口然后列出所有的
 */
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        FragmentModule::class,
        ActivityModule::class
))
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    override fun inject(application: MyApplication)

}