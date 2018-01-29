package com.github.jokar.zhihudaily.room.converter

import android.arch.persistence.room.TypeConverter
import android.text.TextUtils


/**
 * 因为sql中不能插入数组这样类型数据，就需要使用到了类型转换器了,这里使用了TypeConverters；
 * 关于写类型转换器类会与java不同
 * 【坑2】类型转换器使用object 类是不行的，使用普通的class类就可以了；否则生成文件里会报错
 * 【坑2】，看教程里类型转换器是使用的静态方法的，但是在ktolin若你想使用object 类是不行的，
 * 使用普通的class类就可以了；否则生成文件里会报错
 */
class Converters {
    @TypeConverter
    fun arrayToString(array: Array<String>): String {
        if (array == null || array.size === 0) {
            return ""
        }

        val builder = StringBuilder(array[0])
        for (i in 1..array.size - 1) {
            builder.append(",").append(array[i])
        }
        return builder.toString()
    }

    @TypeConverter
    fun StringToArray(value: String): Array<String>? {
        return if (TextUtils.isEmpty(value)) null else value.split(",").toTypedArray()

    }
}