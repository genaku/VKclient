package com.genaku.local

import android.content.Context

class Settings(val context: Context) {

    var token: String by DelegatesExt.preference(context, TOKEN, "")
    var userId: Long by DelegatesExt.preference(context, USER_ID, -1)

    companion object {
        private const val TOKEN = "token"
        private const val USER_ID = "userId"
    }

}
