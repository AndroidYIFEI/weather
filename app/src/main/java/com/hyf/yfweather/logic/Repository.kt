package com.hyf.yfweather.logic

import androidx.lifecycle.liveData
import com.hyf.yfweather.logic.dao.PlaceDao
import com.hyf.yfweather.logic.model.Place
import com.hyf.yfweather.logic.model.Weather
import com.hyf.yfweather.logic.network.YfWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext


/**
 * author: 黄益飞
 * created on: 2020/7/6 11:19
 * description: 仓库层：主要是判断调用方请求数据应该是从网络获取，还是从本地获取， 然后获取到数据返回给调用方
 */
object Repository {
    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
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
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtimeWeather =
                async { YfWeatherNetwork.getRealtimeWeather(lng, lat) }
            val deferredDailyWeather =
                async { YfWeatherNetwork.getDailyWeather(lng, lat) }
            val realtimeResponse = deferredRealtimeWeather.await()
            val dailyResponse = deferredDailyWeather.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status} \ndaily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}