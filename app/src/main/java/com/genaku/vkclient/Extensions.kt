package com.genaku.vkclient

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.view.View

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
fun View.hide() = this.apply { visibility = View.GONE }

fun View.show() = this.apply { visibility = View.VISIBLE }

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {
    val vmFactory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

