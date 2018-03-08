package com.github.jokar.zhihudaily.model.entities.story

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.github.jokar.zhihudaily.room.converter.Converters
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 详情bean
 * 【坑1】 Entity类需要定义一个空 构造函数
 * 如果主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略。
 * 如果构造器有注解，或者有可见度修饰符，这时constructor关键字是必须的，注解和修饰符要放在它之前。
 */
@Entity(tableName = "story")
@TypeConverters(Converters::class)
data class StoryEntity constructor(
        @PrimaryKey
        @SerializedName("id") var id: Int) {
    //次构造函数-----类也可以有二级构造函数，需要加前缀 constructor:
//如果类有主构造函数，每个次构造函数都要，或直接或间接通过另一个次构造函数代理主构造函数。在同一个类中代理另一个构造函数使用 this 关键字
    constructor() : this(0)

    /**
     * Koltin 中的类可以有一个 主构造器
     * Kotlin不允许声明变量但不初始化*****使用一个空变量会报错，使用一个可能为空的变量不检查也会报错
     * 赋非空值----->var str: String = ""---->赋值之后不能将str设为null或其他String?的值，否则编译器会报错
     * 设为null----->var str: String? = null----->每次使用时必须要进行检查，直接使用的话编译器会报错，这样就保证了空安全
     * 强制设为null----->var str: String = null!!
     * -------如果给一个变量赋值为null!!，那么就等于声明这个变量不是空安全的
     * var str:String = null!! -----str.length编译器也不会报错
     *
     * 注意：主构造器的参数可以在初始化代码段中使用，也可以在类主体n定义的属性初始化代码中使用。
     * 一种简洁语法，可以通过主构造器来定义属性并初始化属性值（可以是var或val）：
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