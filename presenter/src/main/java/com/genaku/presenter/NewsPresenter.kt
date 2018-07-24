package com.genaku.presenter

import com.genaku.domain.interfaces.presenters.INewsPresenter

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class NewsPresenter(private val viewModel: INewsPresenter) : INewsPresenter {

    override fun openLogin() {
        viewModel.openLogin()
    }

}