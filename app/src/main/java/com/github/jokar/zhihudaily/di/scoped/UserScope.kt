package com.github.jokar.zhihudaily.di.scoped

import javax.inject.Scope

/**
 * @Scope: Scopes可是非常的有用，Dagger2可以通过自定义注解***限定注解作用域***。
 * 这是一个非常强大的特点，因为就如前面说的一样，没必要让每个对象都去了解如何管理他们的实例。
 */
@Scope
annotation class UserScope