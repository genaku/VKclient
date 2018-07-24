package com.genaku.vkclient.navigation

import com.genaku.vkclient.navigation.screens.NewsScreen

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LoginNavigator(private val navigator: AppNavigator) {

    fun openNews() {
        navigator.goTop()
        navigator.openScreen(NewsScreen())
    }

}