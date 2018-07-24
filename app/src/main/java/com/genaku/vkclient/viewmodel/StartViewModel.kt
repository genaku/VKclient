package com.genaku.vkclient.viewmodel

import android.arch.lifecycle.ViewModel
import com.genaku.domain.StartUseCase
import com.genaku.domain.interfaces.IRepository
import com.genaku.domain.interfaces.presenters.IStartPresenter
import com.genaku.interactor.StartInteractor
import com.genaku.presenter.StartPresenter

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class StartViewModel(repository: IRepository) : ViewModel(), IStartPresenter {

    //OBSERVABLES
    val startLoginEvent = SingleLiveEvent<Boolean>()
    val startNewsEvent = SingleLiveEvent<Boolean>()
    //OBSERVABLES

    val interactor = StartInteractor(
            StartUseCase(
                    repository = repository,
                    presenter = StartPresenter(this)
            )
    )

    override fun openLogin() {
        startLoginEvent.postValue(true)
    }

    override fun openNews() {
        startNewsEvent.postValue(true)
    }

}