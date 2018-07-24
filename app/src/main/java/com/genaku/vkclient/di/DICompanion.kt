package com.genaku.vkclient.di

import android.content.Context
import com.genaku.domain.interfaces.IRepository
import com.genaku.local.LocalRepository
import com.genaku.remote.HttpClient
import com.genaku.remote.RemoteRepositoryFactory
import com.genaku.repository.Repository
import com.genaku.vkclient.navigation.AppNavigator
import com.genaku.vkclient.navigation.AppNavigationFactory

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class DICompanion(applicationContext: Context) {

    val httpClient = HttpClient.getOkHttpClient()

    val repository: IRepository = Repository(
            remoteRepository = RemoteRepositoryFactory.createRemoteRepository(applicationContext, httpClient),
            localRepository = LocalRepository(applicationContext)
    )

    val navigator = AppNavigator(AppNavigationFactory())

}