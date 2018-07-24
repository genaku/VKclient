package com.genaku.vkclient

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.genaku.vkclient.di.DICompanion
import org.mym.plog.DebugPrinter
import org.mym.plog.PLog
import org.mym.plog.config.PLogConfig

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class VkApplication: Application() {
    companion object {
        lateinit var diCompanion: DICompanion
    }

    override fun onCreate() {
        super.onCreate()
        diCompanion = DICompanion(this)

        PLog.init(PLogConfig.Builder()
                .forceConcatGlobalTag(true)
                .needLineNumber(true)
                .useAutoTag(true)
                .needThreadInfo(true)
                .globalTag("IS")
                .build())
        PLog.prepare(DebugPrinter(BuildConfig.DEBUG)) //all logs will be automatically disabled on release version

        AndroidNetworking.initialize(this)
    }

}