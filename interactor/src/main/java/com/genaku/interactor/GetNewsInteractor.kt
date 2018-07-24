package com.genaku.interactor

import com.genaku.domain.interfaces.interactors.IGetNewsInteractor
import com.genaku.domain.interfaces.IRepository
import com.genaku.domain.model.NewsFeedData

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class GetNewsInteractor(private val useCase: IGetNewsInteractor) : IGetNewsInteractor {

    override fun getNewsFeed(startFrom: String, count: Int): NewsFeedData =
            useCase.getNewsFeed(startFrom, count)

}