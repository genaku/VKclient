package com.genaku.repository

import com.genaku.domain.interfaces.IRepository
import com.genaku.domain.model.LoginData
import com.genaku.repository.interfaces.ILocalRepository
import com.genaku.repository.interfaces.IRemoteRepository

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class Repository(
        private val remoteRepository: IRemoteRepository,
        private val localRepository: ILocalRepository
) : IRepository {

    init {
        remoteRepository.setLoginData(localRepository.loginData)
    }

    override val authorized: Boolean
        get() = localRepository.tokenExist()

    override fun setLoginData(data: LoginData) {
        localRepository.setLoginData(data)
        remoteRepository.setLoginData(data)
    }

    override fun logout() {
        localRepository.logout()
        remoteRepository.logout()
    }

    @Throws(Exception::class)
    override fun getNews(startKey: String, count: Int) =
            remoteRepository.getNews(startKey, count)

}