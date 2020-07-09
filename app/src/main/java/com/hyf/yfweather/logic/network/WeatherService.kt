package com.hyf.yfweather.logic.network

import com.hyf.yfweather.App
import com.hyf.yfweather.logic.model.DailyResponse
import com.hyf.yfweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author: 黄益飞
 * created on: 2020/7/8 16:31
 * description: 天气的网络服务
 */
interface WeatherService {
    /**
     * 获取实时天气
     */
    @GET("v2.5/${App.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    /**
     * 获取未来几天天气
     */
    @GET("v2.5/${App.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<DailyResponse>
}