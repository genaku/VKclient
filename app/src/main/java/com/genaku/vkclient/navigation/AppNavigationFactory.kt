package com.genaku.vkclient.navigation

import com.genaku.vkclient.activity.LoginActivity
import com.genaku.vkclient.activity.MainActivity
import com.genaku.vkclient.activity.NewsActivity
import com.genaku.vkclient.activity.PostActivity
import com.genaku.vkclient.navigation.screens.LoginScreen
import com.genaku.vkclient.navigation.screens.MainScreen
import com.genaku.vkclient.navigation.screens.NewsScreen
import com.genaku.vkclient.navigation.screens.PostScreen
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class AppNavigationFactory : RegistryNavigationFactory() {

    init {
        registerActivity<MainScreen>(MainScreen::class.java, MainActivity::class.java)
        registerActivity<LoginScreen>(LoginScreen::class.java, LoginActivity::class.java)
        registerActivity<NewsScreen>(NewsScreen::class.java, NewsActivity::class.java)
        registerActivity<PostScreen>(PostScreen::class.java, PostActivity::class.java)
    }

}