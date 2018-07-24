package com.genaku.remote

class UrlUtils {
    companion object {
        fun getParamValue(url: String, parameterName: String): String =
                url.substringAfter(parameterName).substringBefore(AuthConst.PARAM_DELIMITER)
    }
}