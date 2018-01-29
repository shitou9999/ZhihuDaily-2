package com.github.jokar.zhihudaily.di.module.network

import com.github.jokar.zhihudaily.model.network.services.LatestService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * 先声明一个Vehicle对象并在对象上加上@Inject然后点击AndroidStudio的Build/Make Project；
 * 这样AS会自动帮你生成对应的代码去关联依赖(生成的代码位置在：app/build/generated/source/apt/debug)
 */
@Module
class LatestModule {

    @Provides
    fun latestProvider(retrofit: Retrofit): LatestService {
        return retrofit.create(LatestService::class.java)
    }
}