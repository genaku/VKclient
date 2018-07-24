package com.genaku.vkclient.uimodel

import com.genaku.domain.model.PictureInfo
import com.genaku.domain.model.PostData
import com.genaku.domain.model.RepostData
import com.genaku.vkclient.paging.delegates.NewsViewType
import com.genaku.vkclient.paging.delegates.interfaces.IAdapterViewItem
import java.io.Serializable
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Gena Kuchergin on 23.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
data class UIPostData(
        val id: Long,
        val authorName: String,
        val authorAvatarUrl: String,
        val date: String,
        val text: String,
        val likesCount: String,
        val repostsCount: String,
        val pictures: ArrayList<String>,
        val copyHistory: ArrayList<UIPostData>,
        override val viewType: Int
) : IAdapterViewItem, Serializable

fun PostData.toUI() = with(this) {
    UIPostData(
            id = id,
            authorName = author.name,
            authorAvatarUrl = author.avatarUrl,
            date = dateToString(date),
            text = text,
            likesCount = likesCount.toString(),
            repostsCount = repostsCount.toString(),
            pictures = convertPictures(pictures),
            copyHistory = convertCopyHistory(copyHistory),
            viewType = if (copyHistory.size == 0) NewsViewType.POST else NewsViewType.REPOST
    )
}

private fun convertPictures(pictures: ArrayList<PictureInfo>) =
        pictures.mapTo(ArrayList()) { pictureInfo -> pictureInfo.url }

private fun convertCopyHistory(copyHistory: ArrayList<RepostData>): ArrayList<UIPostData> =
        copyHistory.mapTo(ArrayList()) { it ->
            UIPostData(
                    id = it.id,
                    authorName = it.author.name,
                    authorAvatarUrl = it.author.avatarUrl,
                    date = dateToString(it.date),
                    text = it.text,
                    likesCount = "",
                    repostsCount = "",
                    pictures = convertPictures(it.pictures),
                    copyHistory = ArrayList(),
                    viewType = NewsViewType.POST
            )
        }

private fun dateToString(date: Date): String =
        DateFormat.getInstance().format(date) ?: ""