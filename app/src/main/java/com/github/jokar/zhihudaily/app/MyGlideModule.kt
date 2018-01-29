package com.github.jokar.zhihudaily.app

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.github.jokar.zhihudaily.utils.system.JLog
import java.io.InputStream

/**
 * 图片加载
 * 要触发API生成，请在应用程序中包含一个AppGlideModule实现：
 * AppGlideModule实现必须始终注释@GlideModule。如果注释不存在，则不会发现该模块，
 * 并且您将在日志中看到一条带有Glide日志标记的警告，该日志标记指示该模块无法找到。
 * 请注意添加对 Glide 的注解和注解解析器的依赖：
 * compile 'com.github.bumptech.glideannotations4.0.0'
 * annotationProcessor 'com.github.bumptech.glidecompiler4.0.0'
 */
@GlideModule
class MyGlideModule : AppGlideModule() {

    /**应用程序选项*/
    //内存缓存 Glide使用 LruResourceCache
    //磁盘缓存 builder.setDiskCache(new ExternalDiskCacheFactory(context));
    // builder.setDiskCache(new InternalDiskCacheFactory(context, diskCacheSizeBytes));
//    int diskCacheSizeBytes = 1024  1024  100;  100 MB
//    builder.setDiskCache(new InternalDiskCacheFactory(context, cacheFolderName, diskCacheSizeBytes));
    //默认请求选项 builder.setDefaultRequestOptions
    //未捕获异常策略
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        //        Glide 允许应用通过 AppGlideModule 实现来完全控制 Glide 的内存和磁盘缓存使用
//         MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
//        .setMemoryCacheScreens(2)
//        .build();
//        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
//        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20mb
        builder.setMemoryCache(LruResourceCache(20 * 1024 * 1024))
//        磁盘缓存
//        Glide 使用 DiskLruCacheWrapper 作为默认的 磁盘缓存 。 DiskLruCacheWrapper 是一个使用
//        LRU 算法的固定大小的磁盘缓存。默认磁盘大小为 250 MB ，位置是在应用的 缓存文件夹 中的一个 特定目录 。
        //自定义未捕获异常策略------->UncaughtThrowableStrategy
        val glideUncaughtThrowableStrategy = GlideUncaughtThrowableStrategy()
        builder.setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(glideUncaughtThrowableStrategy))
        builder.setResizeExecutor(GlideExecutor.newSourceExecutor(glideUncaughtThrowableStrategy))
    }
    /**注册组件---应用程序和库都可以注册很多组件来扩展 Glide 的功能*/
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry.replace(GlideUrl::class.java, InputStream::class.java,
                OkHttpUrlLoader.Factory())
//        例如，添加一个 ModelLoader ，使其能从自定义的Model对象中创建一个InputStream
//        registry.append(Photo.class, InputStream.class, new CustomModelLoader.Factory());
    }
}

    /*
    请注意，与Glide.with()类似的选项fitCenter()，并placeholder()提供直接的建设者，
    并不需要传递作为一个独立的RequestOptions对象
        GlideApp.with(fragment)
        .placeholder(R.drawable.placeholder)
        .fitCenter()
        .load(myUrl)
        .into(imageView);


        在加载图片时假如发生了一个异常 (例如, OOM), Glide 将会使用一个 GlideExecutor.UncaughtThrowableStrategy 。
默认策略是将异常打印到设备的 LogCat 中。 这个策略从 Glide 4.2.0 起将可被定制。
 你可以传入一个磁盘执行器和/或一个 resize 执行器：
    final UncaughtThrowableStrategy myUncaughtThrowableStrategy = new ...
    builder.setDiskCacheExecutor(newDiskCacheExecutor(myUncaughtThrowableStrategy));
    builder.setResizeExecutor(newSourceExecutor(myUncaughtThrowableStrategy));
     */

class GlideUncaughtThrowableStrategy : GlideExecutor.UncaughtThrowableStrategy {
    override fun handle(e: Throwable) {
        JLog.e(e)
    }
}