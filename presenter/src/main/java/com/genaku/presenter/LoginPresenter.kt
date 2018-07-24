package com.genaku.presenter

import com.genaku.domain.interfaces.presenters.ILoginPresenter

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LoginPresenter(private val viewModel: ILoginPresenter) : ILoginPresenter {

    override fun openNews() {
        viewModel.openNews()
    }

}