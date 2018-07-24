package com.genaku.vkclient.activity

import com.genaku.vkclient.R
import com.genaku.vkclient.getViewModel
import com.genaku.vkclient.navigation.StartNavigator
import com.genaku.vkclient.viewmodel.StartViewModel

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class MainActivity : NavigationalActivity() {

    private val mStartNavigator = StartNavigator(mNavigator)

    override val layoutResId: Int = R.layout.activity_main

    override fun setupView() {
        getViewModel {
            StartViewModel(mRepository)
        }.apply {
            startLoginEvent.observeWith { mStartNavigator.openLogin() }
            startNewsEvent.observeWith { mStartNavigator.openNews() }
            interactor.doOnStart()
        }
    }

    override fun setupUI() {
    }

}
