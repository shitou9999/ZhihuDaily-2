package com.github.jokar.zhihudaily.di.component.model

import com.github.jokar.zhihudaily.di.component.room.AppDatabaseComponent
import com.github.jokar.zhihudaily.model.event.CollectionModel
import dagger.Component

/**
 * @Component 用来将@Inject和@Module联系起来的桥梁，从@Module中获取依赖并将依赖注入给@Inject
 */
@Component(dependencies = arrayOf(AppDatabaseComponent::class))
interface CollectionModelComponent {

    fun inject(model: CollectionModel)

}