package com.genaku.remote.jsonmodel

import com.genaku.domain.model.Author
import com.genaku.domain.model.PictureInfo
import com.genaku.domain.model.PostData
import com.genaku.domain.model.RepostData
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Gena Kuchergin on 19.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class Post(
        val type: String,

        @SerializedName("source_id")
        val sourceId: Long,

        @SerializedName("post_id")
        val postId: Long,

        @SerializedName("post_type")
        val postType: String,

        val date: Long,
        val text: String,
        val attachments: ArrayList<Attachment>?,
        val likes: Likes,
        val reposts: Reposts,

        @SerializedName("copy_history")
        val copyHistory: ArrayList<Repost>?
)

fun Post.toModel(getAuthor: (Long) -> Author) =
        with(this) {
            PostData(
                    id = postId,
                    author = getAuthor(sourceId),
                    date = getDateFromUnixSeconds(date),
                    text = text,
                    likesCount = likes.count,
                    repostsCount = reposts.count,
                    pictures = getPictures(attachments),
                    copyHistory = getCopyHistory(copyHistory, getAuthor)
            )
        }

private fun getPictures(attachments: ArrayList<Attachment>?): ArrayList<PictureInfo> {
    return attachments?.filter { it.type == "photo" }?.mapTo(ArrayList()) { it ->
        PictureInfo(
                url = getHiResUrl(it.photo)
        )
    } ?: ArrayList()
}

private fun getHiResUrl(photo: Photo): String =
        photo.sizes[photo.sizes.size - 1].url

private fun getCopyHistory(copyHistory: ArrayList<Repost>?, getAuthor: (Long) -> Author): ArrayList<RepostData> {
    return copyHistory?.filter { it.postType == "post" }?.mapTo(ArrayList()) { it ->
        RepostData(
                id = it.id,
                author = getAuthor(it.ownerId),
                date = getDateFromUnixSeconds(it.date),
                text = it.text,
                pictures = getPictures(it.attachments)
        )

    } ?: ArrayList()
}

private fun getDateFromUnixSeconds(unixSeconds: Long): Date =
        Date(unixSeconds * MILLISECONDS)

private const val MILLISECONDS = 1000L
