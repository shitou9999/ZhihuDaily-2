package com.github.jokar.zhihudaily.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.text.TextUtils
import android.view.View
import com.github.jokar.zhihudaily.R
import com.github.jokar.zhihudaily.model.entities.story.StoryEntity
import com.github.jokar.zhihudaily.model.rxbus.RxBus
import com.github.jokar.zhihudaily.model.rxbus.event.UpdateCollectionEvent
import com.github.jokar.zhihudaily.presenter.StoryDetailPresenter
import com.github.jokar.zhihudaily.presenter.base.BasePresenter
import com.github.jokar.zhihudaily.ui.view.common.SingleDataView
import com.github.jokar.zhihudaily.utils.extension.load
import com.github.jokar.zhihudaily.utils.image.ImageLoader
import com.like.LikeButton
import com.like.OnLikeListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_story_detail.*
import kotlinx.android.synthetic.main.common_load.*
import javax.inject.Inject


/**
 * Created by JokAr on 2017/6/25.
 */
class StoryDetailActivity : BaseActivity(), SingleDataView<StoryEntity>,
        NestedScrollView.OnScrollChangeListener {


    companion object {
        fun enter(context: Context, id: Int) {
            val intent = Intent(context, StoryDetailActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }


    @Inject
    lateinit var presenter: StoryDetailPresenter

    val maxHeight = 600;

    var id: Int = 0
    var data: StoryEntity? = null
    var imageHeight: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)
        initToolbar(toolbar, "")

        id = intent.getIntExtra("id", 0)

        init()
    }

    override fun getPresent(): BasePresenter? {
        return presenter
    }

    override fun onWindowInitialized() {
        super.onWindowInitialized()
        getData()
    }

    private fun getData() {
        presenter.getStoryDetail(id)
    }

    fun init() {
        imageHeight = resources.getDimensionPixelOffset(R.dimen.storyDetailImage)
        nestedScrollView.setOnScrollChangeListener(this)

        likeCollect.setOnLikeListener(object : OnLikeListener {
            override fun liked(p0: LikeButton?) {
                data?.collection = 1
                update()
                //通知收藏页面刷新
                RxBus.getInstance().post(UpdateCollectionEvent())
            }

            override fun unLiked(p0: LikeButton?) {
                data?.collection = 0
                update()
                //通知收藏页面刷新
                RxBus.getInstance().post(UpdateCollectionEvent())
            }
        })

        likeButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(p0: LikeButton?) {
                data?.like = 1
                update()
            }

            override fun unLiked(p0: LikeButton?) {
                data?.like = 0
                update()
            }
        })
    }

    /**
     * 更新数据
     */
    fun update() {
        presenter.updateStory(data!!)
    }

    override fun onResume() {
        super.onResume()
        webView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView?.onPause()
    }

    override fun getDataStart() {
        loadView?.visibility = View.VISIBLE
        nestedScrollView?.visibility = View.GONE
        loadView?.showLoad()
    }

    override fun loadData(result: StoryEntity) {

        runOnUiThread({
            data = result
            if (!TextUtils.isEmpty(data?.image)) {
                image.load(data?.image)
                tvAuthor?.text = data?.image_source
                tvTiTle?.text = data?.title
            } else {
                rlTop?.visibility = View.GONE
            }
            //判断是否已经喜欢了
            if (data?.like == 1) {
                likeButton?.isLiked = true
            }

            //判断是否已经收藏了
            if (data?.collection == 1) {
                likeCollect?.isLiked = true
            }

            webView?.loadDataWithBaseURL("", data?.body, "text/html", "utf-8", null)
        })
    }

    override fun loadComplete() {
        runOnUiThread {
            loadView?.visibility = View.GONE
            nestedScrollView?.visibility = View.VISIBLE
        }
    }

    override fun fail(e: Throwable) {
        runOnUiThread {
            loadView?.showError(e.message!!)
        }
    }

    var lastY = 0
    override fun onScrollChange(v: NestedScrollView?, x: Int, y: Int, oldX: Int, oldY: Int) {
        rlTop?.scrollTo(x, -y / 2)

        if (y in 0..maxHeight) {
            toolbar?.alpha = getAlphaForActionBar(y)
        } else {
            if (lastY - y > 20) {
                //上滑
                lastY = y
                if (toolbar?.visibility == View.GONE) {
                    toolbar?.visibility = View.VISIBLE
                }
                toolbar?.alpha = 1f
            } else if (y - lastY > 20) {
                //下滑
                lastY = y
                toolbar?.alpha = 0f
                if (toolbar?.visibility == View.VISIBLE) {
                    toolbar?.visibility = View.GONE
                }
            }
        }
    }

    fun getAlphaForActionBar(scrollY: Int): Float {
        val minDist = 0
        if (scrollY > maxHeight) {
            return 0f
        } else if (scrollY < minDist) {
            return 0f
        } else {
            var alpha = 1f - (1f / maxHeight * scrollY)
            return alpha
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ImageLoader.clear(this, image)
        data = null
        webView?.destroy()
        loadView?.retryListener = null
        nestedScrollView?.setOnClickListener { null }

        likeButton?.setOnLikeListener(null)
        likeCollect?.setOnLikeListener(null)
    }

}



