package com.genaku.remote

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.genaku.domain.interfaces.IRepository
import com.genaku.repository.interfaces.IRemoteRepository
import okhttp3.OkHttpClient

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class RemoteRepositoryFactory {

    companion object {

        fun createRemoteRepository(context: Context, okHttpClient: OkHttpClient): IRemoteRepository {
            AndroidNetworking.initialize(context, okHttpClient)
            return RemoteRepository()
        }

    }

}