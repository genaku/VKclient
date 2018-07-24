package com.genaku.vkclient.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.Toast
import com.genaku.vkclient.R
import com.genaku.vkclient.VkApplication
import me.aartikov.alligator.NavigationContext
import org.mym.plog.PLog

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
abstract class NavigationalActivity(
        private val noActionBar: Boolean = false,
        private val isChildActivity: Boolean = false
) : AppCompatActivity() {

    private var mDoubleBackToExitPressedOnce = false

    protected val mRepository = VkApplication.diCompanion.repository
    protected val mNavigator = VkApplication.diCompanion.navigator

    @get:LayoutRes
    abstract val layoutResId: Int
    abstract fun setupView()
    abstract fun setupUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (noActionBar) {
            hideActionBar()
        }
        setContentView(layoutResId)
        setupView()
        setupUI()
    }

    private fun hideActionBar() {
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()
    }

    override fun onPause() {
        mNavigator.unbind()
        super.onPause()
    }

    // Bind NavigationContext in onResumeFragments() and unbind it in onPause().
    // In a real application you can do it in a base activity class, or use plugin system like that https://github.com/passsy/CompositeAndroid
    override fun onResumeFragments() {
        super.onResumeFragments()
        bindNavigator()
    }

    private fun bindNavigator() {
        val navigationContext = NavigationContext.Builder(this).build()
        mNavigator.bind(navigationContext)
    }

    override fun onBackPressed() {
        if (isChildActivity) {
            mNavigator.back()
        } else {
            handleExit()
        }
    }

    private fun handleExit() {
        if (mDoubleBackToExitPressedOnce || supportFragmentManager.backStackEntryCount != 0) {
            moveTaskToBack(true)
            return
        }

        mDoubleBackToExitPressedOnce = true
        Toast.makeText(this, R.string.press_back_to_exit, Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ mDoubleBackToExitPressedOnce = false }, 2000)
    }

    protected fun <TValue> LiveData<TValue>.observeWith(action: (TValue) -> Unit) {
        this.observe(this@NavigationalActivity, Observer<TValue> {
            it?.apply(action)
        })
    }

}