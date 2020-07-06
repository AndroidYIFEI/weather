package com.hyf.yfweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * author: 黄益飞
 * created on: 2020/7/6 11:04
 * description: 网络请求的封装类 ，利用suspendCoroutine 简化请求代码
 * eg: suspend fun <T> Call<T>.await(): T    该函数主要是利用了 Call<T>的扩展函数
 */
object YfWeatherNetwork {
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}