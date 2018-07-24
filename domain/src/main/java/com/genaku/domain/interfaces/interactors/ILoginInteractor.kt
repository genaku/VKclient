package com.genaku.domain.interfaces.interactors

import com.genaku.domain.model.LoginData

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
interface ILoginInteractor {
    fun setLoginData(data: LoginData)
}