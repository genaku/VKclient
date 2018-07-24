package com.genaku.remote.jsonmodel

import com.google.gson.annotations.SerializedName

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class Profile(
        val id: Long,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("last_name")
        val lastName: String,
        @SerializedName("photo_50")
        val avatarUrl: String
)