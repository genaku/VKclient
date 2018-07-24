package com.genaku.vkclient.activity

import android.os.Handler
import android.os.Message
import android.webkit.*
import com.genaku.domain.interfaces.interactors.ILoginInteractor
import com.genaku.domain.model.LoginData
import com.genaku.remote.AuthConst
import com.genaku.remote.AuthConst.Companion.AUTH_URL
import com.genaku.remote.AuthConst.Companion.TOKEN_PARAM
import com.genaku.remote.AuthConst.Companion.USER_ID_PARAM
import com.genaku.remote.UrlUtils.Companion.getParamValue
import com.genaku.vkclient.R
import com.genaku.vkclient.getViewModel
import com.genaku.vkclient.hide
import com.genaku.vkclient.navigation.LoginNavigator
import com.genaku.vkclient.show
import com.genaku.vkclient.viewmodel.LoginVewModel
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class LoginActivity : NavigationalActivity(
        noActionBar = true
) {

    private var mbErrorOccurred = false
    private var mbReloadPressed = false

    private val mhErrorLayoutHide = ActionHandler {
        layRequestReload.hide()
        webView.show()
    }

    private val mLoginNavigator = LoginNavigator(mNavigator)

    private lateinit var mInteractor: ILoginInteractor

    override val layoutResId: Int = R.layout.activity_login

    override fun setupView() {
        getViewModel {
            LoginVewModel(mRepository)
        }.apply {
            openNewsEvent.observeWith { mLoginNavigator.openNews() }
            mInteractor = interactor
        }
        setupWebView()
    }

    override fun setupUI() {
        showLoginPage()
    }

    private fun setupWebView() {
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = InnerWebViewClient()

        btnReload.setOnClickListener {
            if (mbErrorOccurred) {
                mbReloadPressed = true
                showLoginPage()
                mbErrorOccurred = false
            }
        }
    }

    private fun showLoginPage() {
        webView.loadUrl(AUTH_URL)
    }

    private class ActionHandler(val action: () -> Unit) : Handler() {
        override fun handleMessage(msg: Message) {
            action()
            super.handleMessage(msg)
        }
    }

    inner class InnerWebViewClient : WebViewClient() {

        @Suppress("OverridingDeprecatedMember")
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith(AuthConst.VK_REDIRECT_URI)) {
                val token = getParamValue(url, TOKEN_PARAM)
                val userId = getParamValue(url, USER_ID_PARAM).toLong()
                mInteractor.setLoginData(LoginData(token, userId))
                mNavigator.back()
                return true
            }
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            processError()
        }

        private fun processError() {
            if (!mbErrorOccurred && mbReloadPressed) {
                hideErrorLayout()
                mbReloadPressed = false
            }
        }

        private fun hideErrorLayout() {
            mhErrorLayoutHide.sendEmptyMessageDelayed(10000, 200)
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            mbErrorOccurred = true
            showErrorLayout()
            super.onReceivedError(view, request, error)
        }

        private fun showErrorLayout() {
            layRequestReload.show()
            webView.hide()
        }

    }

}