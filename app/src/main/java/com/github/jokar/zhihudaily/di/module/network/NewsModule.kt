package com.github.jokar.zhihudaily.di.module.network

import com.github.jokar.zhihudaily.model.network.services.NewsServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * @Module 带有此注解的类，用来提供依赖，里面定义一些用@Provides注解的以provide开头的方法，
 * 这些方法就是所提供的依赖，Dagger2会在该类中寻找实例化某个类所需要的依赖。
 */
@Module
open class NewsModule {

    @Provides
    fun provider(retrofit: Retrofit): NewsServices {
        return retrofit.create(NewsServices::class.java)
    }
}