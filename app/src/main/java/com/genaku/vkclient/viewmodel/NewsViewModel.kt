package com.genaku.vkclient.viewmodel

import android.arch.lifecycle.ViewModel
import com.genaku.domain.GetNewsUseCase
import com.genaku.domain.NewsUseCase
import com.genaku.domain.interfaces.IRepository
import com.genaku.domain.interfaces.presenters.IGetNewsPresenter
import com.genaku.domain.interfaces.presenters.INewsPresenter
import com.genaku.interactor.GetNewsInteractor
import com.genaku.interactor.NewsInteractor
import com.genaku.vkclient.VkApplication
import com.genaku.vkclient.paging.PagedListLiveDataFactory

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class NewsViewModel(repository: IRepository) : ViewModel(), INewsPresenter, IGetNewsPresenter {

    private val dataSourceInteractor = GetNewsInteractor(GetNewsUseCase(
            repository = VkApplication.diCompanion.repository,
            presenter = this
    ))

    //OBSERVABLES
    val backToLoginEvent = SingleLiveEvent<Boolean>()
    val newsLiveData = PagedListLiveDataFactory.createPagedPostFeedLiveData(dataSourceInteractor)
    val errorEvent = SingleLiveEvent<Boolean>()
    val refreshingState = SingleLiveEvent<Boolean>()
    //OBSERVABLES

    val interactor = NewsInteractor(
            NewsUseCase(
                    repository = repository,
                    presenter = this
            )
    )

    init {
        refreshingState.postValue(true)
    }

    override fun openLogin() {
        backToLoginEvent.postValue(true)
    }

    override fun showError() {
        errorEvent.postValue(true)
    }

}