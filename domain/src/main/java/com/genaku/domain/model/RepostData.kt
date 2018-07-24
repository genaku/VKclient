package com.genaku.domain.model

import java.util.*

data class RepostData(
        val id: Long,
        val author: Author,
        val date: Date,
        val text: String,
        val pictures: ArrayList<PictureInfo>
)