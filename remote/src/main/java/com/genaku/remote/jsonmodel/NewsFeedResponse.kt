package com.genaku.remote.jsonmodel

import com.google.gson.annotations.SerializedName

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class NewsFeedResponse(
        val items: ArrayList<Post>?,
        val profiles: ArrayList<Profile>?,
        val groups: ArrayList<VkGroup>?,
        @SerializedName("next_from")
        val next_from: String?
)