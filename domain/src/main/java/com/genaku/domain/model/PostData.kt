package com.genaku.domain.model

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class PostData(
        val id: Long,
        val author: Author,
        val date: Date,
        val text: String,
        val likesCount: Int,
        val repostsCount: Int,
        val pictures: ArrayList<PictureInfo>,
        val copyHistory: ArrayList<RepostData>
)