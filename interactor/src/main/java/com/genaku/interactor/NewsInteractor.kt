package com.genaku.interactor

import com.genaku.domain.NewsUseCase
import com.genaku.domain.interfaces.interactors.INewsInteractor
import org.jetbrains.anko.doAsync

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class NewsInteractor(private val useCase: NewsUseCase) : INewsInteractor {

    override fun logout() {
        doAsync {
            useCase.logout()
        }
    }

}