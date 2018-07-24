package com.genaku.presenter

import com.genaku.domain.interfaces.presenters.IStartPresenter

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class StartPresenter(private val viewModel: IStartPresenter) : IStartPresenter {

    override fun openLogin() {
        viewModel.openLogin()
    }

    override fun openNews() {
        viewModel.openNews()
    }

}