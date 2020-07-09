package com.hyf.yfweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.hyf.yfweather.App
import com.hyf.yfweather.logic.model.Place

/**
 * author: 黄益飞
 * created on: 2020/7/9 10:14
 * description: 缓存搜索的城市到本地
 */
object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        App.context.getSharedPreferences("yfweather", Context.MODE_PRIVATE)
}