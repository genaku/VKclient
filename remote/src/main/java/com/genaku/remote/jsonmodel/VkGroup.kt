package com.genaku.remote.jsonmodel

import com.google.gson.annotations.SerializedName

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class VkGroup(
        val id: Long,
        val name: String,
        @SerializedName("photo_50")
        val avatarUrl: String
)