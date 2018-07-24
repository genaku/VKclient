package com.genaku.remote

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
enum class RemoteRepoError {
    UNKNOWN_ERROR,
    WRONG_CERTIFICATE,
    SERVER_CONNECTION_ERROR,
    REQUEST_CANCELED,
    HTTP_CLIENT_STATUS_500,
    HTTP_CLIENT_STATUS_404,
    HTTP_CLIENT_STATUS_403,
    NETWORK_STORAGE_JSON_ERROR,
    VK_ERROR
}