package com.hyf.yfweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hyf.yfweather.logic.Repository
import com.hyf.yfweather.logic.model.Place

/**
 * author: 黄益飞
 * created on: 2020/7/6 11:39
 * description: 城市viewmodel 层
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}