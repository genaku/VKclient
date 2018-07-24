package com.genaku.domain

import com.genaku.domain.interfaces.interactors.ILoginInteractor
import com.genaku.domain.interfaces.presenters.ILoginPresenter
import com.genaku.domain.interfaces.IRepository
import com.genaku.domain.model.LoginData

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LoginUseCase(
        private val repository: IRepository,
        private val presenter: ILoginPresenter
): ILoginInteractor {

    override fun setLoginData(data: LoginData) {
        repository.setLoginData(data)
        presenter.openNews()
    }

}