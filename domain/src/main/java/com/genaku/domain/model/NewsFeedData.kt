package com.genaku.domain.model

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class NewsFeedData(
        val prevKey: String,
        val nextKey: String,
        val items: ArrayList<PostData>
)