package com.github.jokar.zhihudaily.presenter

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.NonNull
import com.github.jokar.zhihudaily.model.entities.theme.ThemeEntity
import com.github.jokar.zhihudaily.model.event.ThemeModel
import com.github.jokar.zhihudaily.presenter.base.BasePresenter
import com.github.jokar.zhihudaily.presenter.base.SingleDataViewCallBack
import com.github.jokar.zhihudaily.ui.view.common.SingleDataView
import javax.inject.Inject

/**
 * Created by JokAr on 2017/7/2.
 */
class ThemePresenter @Inject constructor(var model: ThemeModel?,
                                         var view: SingleDataView<ThemeEntity>?)
    : BasePresenter {


    fun getTheme(id: Int, @NonNull lifecycle: LifecycleOwner) {
        model?.getTheme(id, lifecycle, SingleDataViewCallBack(view))
    }

    override fun destroy() {
        model = null
        view = null
    }

}