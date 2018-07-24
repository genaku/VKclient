package com.genaku.presenter

import com.genaku.domain.interfaces.presenters.IGetNewsPresenter

class GetNewsPresenter(private val viewModel: IGetNewsPresenter) : IGetNewsPresenter {

    override fun showError() =
            viewModel.showError()

}