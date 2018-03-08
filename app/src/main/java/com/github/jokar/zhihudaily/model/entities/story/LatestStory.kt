package com.github.jokar.zhihudaily.model.entities.story

import com.google.gson.annotations.SerializedName

/**
 * 数据类
 * 编译器会自动的从主构造函数中根据所有声明的属性提取以下函数
 * equals() / hashCode()
 * toString() 格式如 "User(name=John, age=42)"
 * componentN() functions 对应于属性，按声明顺序排列
 * copy() 函数
 * 如果这些函数在类中已经被明确定义了，或者从超类中继承而来，就不再会生成
 * 数据类需要满足以下条件：
 * 1.主构造函数至少包含一个参数。
 * 2.所有的主构造函数的参数必须标识为val 或者 var ;
 * 3.数据类不可以声明为 abstract, open, sealed 或者 inner;
 * 4.数据类不能继承其他类 (但是可以实现接口)。
 */
data class LatestStory(@SerializedName("date") var date: Long,
                       @SerializedName("stories") var stories: ArrayList<StoryEntity>?,
                       @SerializedName("top_stories") var top_stories: ArrayList<TopStoryEntity>?)

//复制使用 copy() 函数，我们可以使用该函数复制对象并修改部分属性
//data class User(val name: String, val age: Int)
//fun copy(name: String = this.name, age: Int = this.age) = User(name, age)

//数据类以及解构声明
//组件函数允许数据类在解构声明中使用
//val jane = User("Jane", 35)
//val (name, age) = jane
//println("$name, $age years of age") // prints "Jane, 35 years of age"