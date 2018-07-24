package com.genaku.vkclient.navigation

import android.app.Activity
import android.content.Intent
import com.genaku.vkclient.navigation.screens.MainScreen
import me.aartikov.alligator.*

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class AppNavigator(navigationFactory: AppNavigationFactory) {

    private val mNavigator = AndroidNavigator(navigationFactory)
    private val mScreenResolver = mNavigator.screenResolver!!

    fun bind(navigationContext: NavigationContext?) =
            mNavigator.bind(navigationContext)

    fun unbind() =
            mNavigator.unbind()

    fun back() {
        mNavigator.goBack()
    }

    fun goTop() {
        mNavigator.goBackTo(MainScreen::class.java)
    }

    fun openScreen(screen: Screen) {
        mNavigator.goForward(screen)
    }

    fun <ScreenT : Screen> getScreen(activity: Activity): ScreenT =
            mScreenResolver.getScreen(activity)

}