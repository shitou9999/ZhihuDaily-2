package com.github.jokar.zhihudaily.di.module

import com.github.jokar.zhihudaily.BuildConfig
import com.github.jokar.zhihudaily.model.network.log.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Singleton 表示注解指明对象只能有一个实例
 */
@Module
class NetworkModule {
    private val DEFAULT_TIMEOUT = 15
    private val BASE_URL = "http://news-at.zhihu.com/api/4/"

    @Singleton
    @Provides
    fun providerRetrofit(): Retrofit {
        val interceptor = LoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptor.level = LoggingInterceptor.Level.BODY
        else
            interceptor.level = LoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
