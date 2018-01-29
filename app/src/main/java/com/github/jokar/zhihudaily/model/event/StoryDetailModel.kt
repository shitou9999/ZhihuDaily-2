package com.github.jokar.zhihudaily.model.event

import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.github.jokar.zhihudaily.app.MyApplication
import com.github.jokar.zhihudaily.di.component.network.DaggerNewsComponent
import com.github.jokar.zhihudaily.di.component.room.DaggerAppDatabaseComponent
import com.github.jokar.zhihudaily.di.module.network.NewsModule
import com.github.jokar.zhihudaily.di.module.room.AppDatabaseModule
import com.github.jokar.zhihudaily.model.entities.story.StoryEntity
import com.github.jokar.zhihudaily.model.event.callback.SingleDataCallBack
import com.github.jokar.zhihudaily.model.network.result.SingleResourceObserver
import com.github.jokar.zhihudaily.model.network.services.NewsServices
import com.github.jokar.zhihudaily.room.AppDatabaseHelper
import com.github.jokar.zhihudaily.utils.HtmlUtil
import com.github.jokar.zhihudaily.utils.system.JLog
import com.sunagy.mazcloud.utlis.rxjava.SchedulersUtil
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by JokAr on 2017/6/25.
 */
class StoryDetailModel(var activity: AppCompatActivity) {

    @Inject
    lateinit var retrofit: Retrofit
    @Inject
    lateinit var service: NewsServices
    //room
    @Inject
    lateinit var mDatabaseHelper: AppDatabaseHelper

    init {

        val component = DaggerAppDatabaseComponent.builder()
                .appDatabaseModule(AppDatabaseModule(activity.applicationContext))
                .build()

        DaggerNewsComponent
                .builder()
                .networkComponent(MyApplication.getNetComponent())
                .appDatabaseComponent(component)
                .newsModule(NewsModule())
                .build()
                .inject(this)
    }

    fun getStoryDetail(id: Int,
                       @NonNull callBack: SingleDataCallBack<StoryEntity>) {

        checkNotNull(callBack)

        Observable.create(ObservableOnSubscribe<StoryEntity> { e ->
            var story = mDatabaseHelper.getStory(id)
            //判断本地是否有
            if (story == null) {
                story = StoryEntity(id)
                story.images = arrayOf("")
                mDatabaseHelper.insertStory(story)
            }
            e.onNext(story)
        }).filter { story ->
            //判断本地是否有详细数据
            if (!TextUtils.isEmpty(story.body)) {
                callBack.data(story)
                callBack.onComplete()
                return@filter false
            }
            return@filter true
        }.flatMap {
            //没有从网络获取
            service.getNews(id)
        }.doOnDispose {
            JLog.d("getStoryDetail dispose")
        }.bindUntilEvent(activity, Lifecycle.Event.ON_DESTROY)
                .compose(SchedulersUtil.applySchedulersIO())
                .map { (body, image_source, title, image, share_url, js, images, _, css) ->
                    //添加格式到body
                    var story = mDatabaseHelper.getStory(id)
                    story.body = HtmlUtil.createHtmlData(css, js, body)
                    story.image_source = image_source
                    story.image = image
                    story.share_url = share_url
                    if (TextUtils.isEmpty(story.title)) {
                        story.title = title
                        story.images = images
                    }
                    //更新本地数据
                    mDatabaseHelper.updateStory(story)
                    //传递story
                    story
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SingleResourceObserver(callBack))
    }


    /**
     * 更新story
     */
    fun updateStory(story: StoryEntity) {
        Observable.just(story)
                .bindUntilEvent(activity, Lifecycle.Event.ON_DESTROY)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .subscribe { mDatabaseHelper.updateStory(story) }
    }
}