package com.genaku.vkclient.navigation

import com.genaku.vkclient.navigation.screens.LoginScreen
import com.genaku.vkclient.navigation.screens.PostScreen
import com.genaku.vkclient.uimodel.UIPostData

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class NewsNavigator(private val navigator: AppNavigator) {

    fun backToLogin() {
        navigator.goTop()
        navigator.openScreen(LoginScreen())
    }

    fun openPost(postData: UIPostData) {
        navigator.openScreen(PostScreen(postData))
    }

}