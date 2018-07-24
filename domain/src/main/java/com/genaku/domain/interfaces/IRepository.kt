package com.genaku.domain.interfaces

import com.genaku.domain.model.LoginData
import com.genaku.domain.model.NewsFeedData

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
interface IRepository {
    val authorized: Boolean
    fun setLoginData(data: LoginData)
    fun logout()
    fun getNews(startKey: String, count: Int): NewsFeedData
}