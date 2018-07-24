package com.genaku.repository.interfaces

import com.genaku.domain.model.LoginData
import com.genaku.domain.model.NewsFeedData

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
interface IRemoteRepository {
    fun setLoginData(data: LoginData)
    fun logout()
    fun getNews(startFrom: String, count: Int): NewsFeedData
}