package com.hyf.yfweather.logic.model

/**
 * author: 黄益飞
 * created on: 2020/7/8 16:30
 * description: 天气的封装类 数据模型
 */
class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)