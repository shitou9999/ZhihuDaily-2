package com.github.jokar.zhihudaily.model.entities.story

import android.arch.persistence.room.*
import com.github.jokar.zhihudaily.room.converter.Converters
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 详情bean
 * 【坑1】 Entity类需要定义一个空 构造函数
 */
@Entity(tableName = "story")
@TypeConverters(Converters::class)
data class StoryEntity constructor(
        @PrimaryKey
        @SerializedName("id") var id: Int) {

    constructor() : this(0)

    /**
     * Kotlin不允许声明变量但不初始化*****使用一个空变量会报错，使用一个可能为空的变量不检查也会报错
     * 赋非空值----->var str: String = ""---->赋值之后不能将str设为null或其他String?的值，否则编译器会报错
     * 设为null----->var str: String? = null----->每次使用时必须要进行检查，直接使用的话编译器会报错，这样就保证了空安全
     * 强制设为null----->var str: String = null!!
     * -------如果给一个变量赋值为null!!，那么就等于声明这个变量不是空安全的
     * var str:String = null!! -----str.length编译器也不会报错
     */
//    var str: String = ""
    // str 不是空的，可以直接使用不必检查是否为空
    // 可以随意给str赋非空值
//    str = "hello kotlin"
    // 这样编译器是会报错的 str = null

// 如果强制设为null，str就不再是空安全的，编译也就不会检查str了str = null!!

// 声明str2为可空的变量，str2也是空安全的
//    var str2: String? = null
    // 直接使用str2编译器会报错sr2.length
    // 可以这样用str2?.let {  }

    @ColumnInfo(name = "images")
    @SerializedName("images")
    var images: Array<String>? = null

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null

    @ColumnInfo(name = "date")
    var date: Long = 0

    @ColumnInfo(name = "read")
    var read: Int = 0

    @ColumnInfo(name = "like")
    var like: Int = 0

    @ColumnInfo(name = "collection")
    var collection: Int = 0

    /**
     * HTML-Body
     */
    @ColumnInfo(name = "body")
    var body: String? = null

    /**
     * 图片作者
     */
    @ColumnInfo(name = "image_source")
    var image_source: String? = null

    /**
     * 详情头部image
     */
    @ColumnInfo(name = "image")
    var image: String? = null

    /**
     * 时间-文字
     */
    @ColumnInfo(name = "date_string")
    var dateString: String = ""

    /**
     * 分享url
     */
    @ColumnInfo(name = "share_url")
    var share_url: String? = null

    override fun toString(): String {
        return "StoryEntity(id=$id, images=${Arrays.toString(images)}," +
                " title=$title," +
                " date=$date," +
                " read=$read," +
                " like=$like," +
                " collection=$collection," +
                " body=$body," +
                " image_source=$image_source," +
                " image=$image," +
                " dateString='$dateString'," +
                " share_url=$share_url)"
    }




}