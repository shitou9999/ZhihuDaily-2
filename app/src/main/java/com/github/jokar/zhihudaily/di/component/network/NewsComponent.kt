package com.github.jokar.zhihudaily.di.component.network

import com.github.jokar.zhihudaily.di.component.room.AppDatabaseComponent
import com.github.jokar.zhihudaily.di.module.network.NewsModule
import com.github.jokar.zhihudaily.di.scoped.UserScope
import com.github.jokar.zhihudaily.model.event.StoryDetailModel
import dagger.Component

/**
 * @Component 注解的接口连接依赖的提供者 @Component(modules = {VehicleModule.class})
 */
@UserScope
@Component(dependencies = arrayOf(NetworkComponent::class,
        AppDatabaseComponent::class),
        modules = arrayOf(NewsModule::class))
interface NewsComponent {

    fun inject(model: StoryDetailModel)

}