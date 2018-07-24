package com.genaku.remote.jsonmodel

import com.google.gson.annotations.SerializedName

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class Photo(
        val id: Long,
        val text: String,
        val sizes: ArrayList<PhotoInfo>,
        val date: Long,

        @SerializedName("access_key")
        val accessKey: String
)