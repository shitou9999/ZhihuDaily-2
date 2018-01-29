package com.github.jokar.zhihudaily.utils

/**
 * 不带参数
 */
class Singleton private constructor() {

    init { println("This ($this) is a singleton") }

    private object Holder { val INSTANCE = Singleton() }

    companion object {
        val instance: Singleton by lazy { Holder.INSTANCE }
    }
    var b:String? = null

/*
    init { println("This ($this) is a singleton") }

    companion object {
        val instance: = Singleton()
    }
    var b:String? = null
    */


}