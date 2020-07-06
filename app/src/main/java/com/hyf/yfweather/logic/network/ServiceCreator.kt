package com.hyf.yfweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author: 黄益飞
 * created on: 2020/7/6 11:00
 * description: 网络请求的构建器
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}