package com.genaku.domain.interfaces.interactors

import com.genaku.domain.model.NewsFeedData
import java.util.*

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
interface IGetNewsInteractor {
    fun getNewsFeed(startFrom: String, count: Int): NewsFeedData
}