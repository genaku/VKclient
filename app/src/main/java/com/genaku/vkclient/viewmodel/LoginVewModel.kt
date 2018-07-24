package com.genaku.vkclient.viewmodel

import android.arch.lifecycle.ViewModel
import com.genaku.domain.LoginUseCase
import com.genaku.domain.interfaces.presenters.ILoginPresenter
import com.genaku.domain.interfaces.IRepository
import com.genaku.interactor.LoginInteractor
import com.genaku.presenter.LoginPresenter

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LoginVewModel(repository: IRepository) : ViewModel(), ILoginPresenter {

    //OBSERVABLES
    val openNewsEvent = SingleLiveEvent<Boolean>()
    //OBSERVABLES

    val interactor = LoginInteractor(LoginUseCase(repository, LoginPresenter(this)))

    override fun openNews() {
        openNewsEvent.postValue(true)
    }
}