package com.hyf.yfweather.logic.network

import com.hyf.yfweather.App
import com.hyf.yfweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author: 黄益飞
 * created on: 2020/7/6 10:54
 * description:
 */
interface PlaceService {
    /**
     * 搜索城市
     */
    @GET("v2/place?token=${App.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}