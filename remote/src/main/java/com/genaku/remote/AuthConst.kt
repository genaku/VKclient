package com.genaku.remote

/**
 * Created by Gena Kuchergin on 19.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class AuthConst {
    companion object {
        private const val VK_CLENT_ID = "6637979"
        private const val VK_SCOPE = "friends,wall,photos,offline"
        private const val VK_DISPLAY = "mobile"
        private const val RESPONSE_TYPE = "token"

        const val VK_API_VERSION = "5.80"
        const val VK_REDIRECT_URI = "https://oauth.vk.com/blank.html"

        const val TOKEN_PARAM = "access_token="
        const val USER_ID_PARAM = "user_id="
        const val PARAM_DELIMITER = "&"

        const val AUTH_URL = "https://oauth.vk.com/authorize?client_id=${AuthConst.VK_CLENT_ID}&display=${AuthConst.VK_DISPLAY}&redirect_uri=${AuthConst.VK_REDIRECT_URI}&response_type=${AuthConst.RESPONSE_TYPE}&v=${AuthConst.VK_API_VERSION}&scope=${AuthConst.VK_SCOPE}"
    }
}