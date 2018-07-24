package com.genaku.interactor

import com.genaku.domain.LoginUseCase
import com.genaku.domain.interfaces.interactors.ILoginInteractor
import com.genaku.domain.model.LoginData
import org.jetbrains.anko.doAsync

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LoginInteractor(private val useCase: LoginUseCase): ILoginInteractor {

    override fun setLoginData(data: LoginData) {
        doAsync {
            useCase.setLoginData(data)
        }
    }

}