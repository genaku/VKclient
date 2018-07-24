package com.genaku.domain

import com.genaku.domain.interfaces.IRepository
import com.genaku.domain.interfaces.interactors.IStartInteractor
import com.genaku.domain.interfaces.presenters.IStartPresenter

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class StartUseCase(val repository: IRepository, val presenter: IStartPresenter) : IStartInteractor {

    override fun doOnStart() {
        if (repository.authorized) {
            presenter.openNews()
        } else {
            presenter.openLogin()
        }
    }

}