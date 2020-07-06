package com.hyf.yfweather

import android.app.Application
import android.content.Context

/**
 * author: 黄益飞
 * created on: 2020/7/6 10:16
 * description:
 */
class App : Application() {
    companion object{
        //这是彩云api的token，类似于appkey
        const val TOKEN = "BNBRvBveaD2VfHVI"
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}