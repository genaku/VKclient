package com.genaku.remote

import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError
import javax.net.ssl.SSLHandshakeException

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
fun ANError.toException(): RemoteRepoException =
        when (this.errorCode) {
            0 -> when (this.errorDetail) {
                ANConstants.CONNECTION_ERROR -> {
                    if (this@toException.cause?.cause is SSLHandshakeException) {
                        RemoteRepoException(RemoteRepoError.WRONG_CERTIFICATE)
                    } else {
                        RemoteRepoException(RemoteRepoError.SERVER_CONNECTION_ERROR)
                    }
                }
                ANConstants.RESPONSE_FROM_SERVER_ERROR -> RemoteRepoException(RemoteRepoError.SERVER_CONNECTION_ERROR)
                ANConstants.REQUEST_CANCELLED_ERROR -> RemoteRepoException(RemoteRepoError.REQUEST_CANCELED)
                ANConstants.PARSE_ERROR -> RemoteRepoException(RemoteRepoError.NETWORK_STORAGE_JSON_ERROR)
                else -> RemoteRepoException(RemoteRepoError.UNKNOWN_ERROR)
            }
            500 -> RemoteRepoException(RemoteRepoError.HTTP_CLIENT_STATUS_500)
            404 -> RemoteRepoException(RemoteRepoError.HTTP_CLIENT_STATUS_404)
            403 -> RemoteRepoException(RemoteRepoError.HTTP_CLIENT_STATUS_403)
            else -> RemoteRepoException(RemoteRepoError.UNKNOWN_ERROR)
        }
