package com.hyf.yfweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * author: 黄益飞
 * created on: 2020/7/6 10:47
 * description: 城市的数据模型
 * eg: @SerializedName("key") 表示 将后台返回的字段 “key” 转换成我们自己定义的属性名
 */
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String,
    val loation: Location,
    @SerializedName("formatted_address") val address: String
)
data class Location(val lng:String,val lat:String)