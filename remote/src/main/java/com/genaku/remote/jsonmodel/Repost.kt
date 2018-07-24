package com.genaku.remote.jsonmodel

import com.google.gson.annotations.SerializedName

data class Repost(
        val id: Long,

        @SerializedName("owner_id")
        val ownerId: Long,

        @SerializedName("post_type")
        val postType: String,

        val date: Long,
        val text: String,
        val attachments: ArrayList<Attachment>?
)