package com.genaku.domain

import com.genaku.domain.interfaces.interactors.INewsInteractor
import com.genaku.domain.interfaces.presenters.INewsPresenter
import com.genaku.domain.interfaces.IRepository

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class NewsUseCase(
        private val repository: IRepository,
        private val presenter: INewsPresenter
): INewsInteractor {

    override fun logout() {
        repository.logout()
        presenter.openLogin()
    }

}