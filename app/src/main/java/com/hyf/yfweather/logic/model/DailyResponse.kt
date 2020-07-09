package com.hyf.yfweather.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * author: 黄益飞
 * created on: 2020/7/8 16:21
 * description: 未来几天天气的数据模型
 */
class DailyResponse(val status: String, val result: Result) {

    class Result(val daily: Daily)

    class Daily(
        val temperature: List<Temperature>,
        val skycon: List<Skycon>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    class Temperature(val max: Float, val min: Float)

    class Skycon(val value: String, val date: Date)

    class LifeIndex(
        val coldRisk: List<Desc>,
        val carWashing: List<Desc>,
        val ultraviolet: List<Desc>,
        val dressing: List<Desc>
    )

    class Desc(val desc: String)
}