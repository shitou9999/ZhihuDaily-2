package com.github.jokar.zhihudaily

/**
 * Created by cyh on 2018/1/31.
 */
class test {

/*

Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，其实 Kotlin 帮你封装了一个对象，
这样可以保证不会出现空指针。数字类型也一样，所有在比较两个数字的时候，就有比较数据大小和比较两个对象是否相同的区别了。

在 Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。

    val a: Int = 10000
    println(a === a) // true，值相等，对象地址相等

    //经过了装箱，创建了两个不同的对象
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    //虽然经过了装箱，但是值是相等的，都是10000
    println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
    println(boxedA == anotherBoxedA) // true，值相等

    for循环 接受的参数是变量和一个范围 使用in关键字
    for(i in 1..10){
       //i
    }
    // .. 表示从1到10的范围
    for (i in array.indices) {
        print(array[i])
    }
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }

    while 循环 和java一样
    在循环中的 break 和 continue 语句 和java一样

    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }

    when (x) {
        in 1..10 -> print("x is in the range")
        in validNumbers -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }
//in关键字 检查值是否在或不在范围内或集合中
*/

}