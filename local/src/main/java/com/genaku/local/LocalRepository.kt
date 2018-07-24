package com.genaku.local

import android.content.Context
import com.genaku.domain.model.LoginData
import com.genaku.repository.interfaces.ILocalRepository

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LocalRepository(context: Context) : ILocalRepository {

    private val mSettings = Settings(context)

    override val loginData: LoginData
        get() = LoginData(mSettings.token, mSettings.userId)

    override fun setLoginData(data: LoginData) {
        mSettings.token = data.token
        mSettings.userId = data.userId
    }

    override fun logout() {
        mSettings.token = ""
        mSettings.userId = -1
    }

    override fun tokenExist(): Boolean =
            mSettings.token.isNotEmpty()

}