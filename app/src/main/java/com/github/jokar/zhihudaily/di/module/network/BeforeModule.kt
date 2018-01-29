package com.github.jokar.zhihudaily.di.module.network

import com.github.jokar.zhihudaily.model.network.services.BeforeService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * @Provide: 在modules中，我们定义的方法是用这个注解，以此来告诉Dagger我们想要构造对象并提供这些依赖
 */
@Module
class BeforeModule {

    @Provides
    fun beforeProvider(retrofit: Retrofit):BeforeService{
        return retrofit.create(BeforeService::class.java)
    }
}