package com.hyf.yfweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * author: 黄益飞
 * created on: 2020/7/8 16:15
 * description: 实时天气的数据模型
 */
class RealtimeResponse(val status: String, val result: Result) {

    class Result(val realtime: Realtime)

    class Realtime(
        val temperature: Float,
        val skycon: String,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    class AirQuality(val aqi: AQI)

    class AQI(val chn: Float)
}