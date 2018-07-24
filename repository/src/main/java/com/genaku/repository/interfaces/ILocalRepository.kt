package com.genaku.repository.interfaces

import com.genaku.domain.model.LoginData

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
interface ILocalRepository {
    val loginData: LoginData
    fun setLoginData(data: LoginData)
    fun logout()
    fun tokenExist(): Boolean
}