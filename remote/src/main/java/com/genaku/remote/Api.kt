package com.genaku.remote

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANResponse
import com.genaku.domain.model.LoginData
import com.genaku.remote.jsonmodel.ErrorResponse
import com.genaku.remote.jsonmodel.NewsFeedJson
import com.genaku.remote.jsonmodel.NewsFeedResponse
import org.mym.plog.PLog

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class Api {

    private var mUserId: String = "-1"
    private var mToken: String = ""

    fun setLoginData(data: LoginData) {
        mToken = data.token
        mUserId = data.userId.toString()
    }

    private fun requestBuilder(url: String) = AndroidNetworking.get(url)
            .addQueryParameter("access_token", mToken)
            .addQueryParameter("v", AuthConst.VK_API_VERSION)

    @Suppress("UNCHECKED_CAST")
    @Throws(RemoteRepoException::class)
    private fun processANResponse(response: ANResponse<Any>): NewsFeedResponse {
        if (response.isSuccess) {
            val result = response.result as NewsFeedJson
            if (result.response != null) {
                return result.response
            }
            if (result.error != null) {
                throw RemoteRepoException(getVKError(result.error))
            } else {
                throw RemoteRepoException(RemoteRepoError.NETWORK_STORAGE_JSON_ERROR)
            }
        } else {
            throw response.error.toException()
        }
    }

    private fun getVKError(error: ErrorResponse): RemoteRepoError {
        PLog.d("Error: $error")
        // TODO map vk api errors to RemoteRepoError enum
        return RemoteRepoError.VK_ERROR
    }

    @Throws(RemoteRepoException::class)
    fun getNews(startFrom: String, count: Int): NewsFeedResponse {
        val builder = requestBuilder(GET_NEWS_URL)
        val request = builder.addQueryParameter("filters", "post")
                .addQueryParameter("start_from", startFrom)
                .addQueryParameter("count", count.toString())
                .build()
        val response = request.executeForObject(NewsFeedJson::class.java)
        return processANResponse(response)
    }

    companion object {
        private const val API_URL = "https://api.vk.com/method/"
        private const val GET_NEWS_URL = API_URL + "newsfeed.get"
    }

}
