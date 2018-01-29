package com.github.jokar.zhihudaily.utils

/**
 * 带参数
 */
class SingletonTwo private constructor(str: String){

    var string: String = str;

    init {
        println("str is $str")
        println("string is $string")
    }

    companion object {
        @Volatile
        var instance: SingletonTwo? = null

        fun getInstance(c: String): SingletonTwo {
            if (instance == null) {
                synchronized(Singleton::class) {
                    if (instance == null) {
                        instance = SingletonTwo(c)
                    }
                }
            }
            return instance!!
        }
    }



}