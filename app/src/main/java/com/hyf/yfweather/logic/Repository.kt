package com.hyf.yfweather.logic

import androidx.lifecycle.liveData
import com.hyf.yfweather.logic.model.Place
import com.hyf.yfweather.logic.network.YfWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException


/**
 * author: 黄益飞
 * created on: 2020/7/6 11:19
 * description: 仓库层：主要是判断调用方请求数据应该是从网络获取，还是从本地获取， 然后获取到数据返回给调用方
 */
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = YfWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(
                    RuntimeException(
                        "response status is " +
                                "${placeResponse.status}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure<List<com.hyf.yfweather.logic.model.Place>>(e)
        }
        emit(result as Result<List<Place>>)
    }
}