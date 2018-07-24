package com.genaku.vkclient.navigation

import com.genaku.vkclient.navigation.screens.LoginScreen
import com.genaku.vkclient.navigation.screens.NewsScreen

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class StartNavigator(private val navigator: AppNavigator) {

    fun openLogin() {
        navigator.openScreen(LoginScreen())
    }

    fun openNews() {
        navigator.openScreen(NewsScreen())
    }

}