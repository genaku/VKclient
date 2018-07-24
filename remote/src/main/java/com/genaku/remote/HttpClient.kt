package com.genaku.remote

import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class HttpClient {

    companion object {

        fun getOkHttpClient(): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()
//            addDebugLoggers(clientBuilder)
            return clientBuilder
                    .build()
        }

        private fun addDebugLoggers(clientBuilder: OkHttpClient.Builder) {
            // add an interceptor for logging OkHttp in Logcat
            clientBuilder.addNetworkInterceptor(HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            )
            // add an network interceptor for debugging from Chrome browser
            clientBuilder.addNetworkInterceptor(StethoInterceptor())
        }

    }

}