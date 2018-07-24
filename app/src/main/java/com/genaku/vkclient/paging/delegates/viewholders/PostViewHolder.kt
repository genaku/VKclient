package com.genaku.vkclient.paging.delegates.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.genaku.vkclient.R
import org.jetbrains.anko.find

open class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvText = view.find<TextView>(R.id.tvText)
    val tvAuthor = view.find<TextView>(R.id.tvAuthor)
    val tvDate = view.find<TextView>(R.id.tvDate)
    val tvLikes = view.find<TextView>(R.id.tvLikes)
    val tvReposts = view.find<TextView>(R.id.tvReposts)
    val imgAuthor = view.find<ImageView>(R.id.imgAuthor)
    val imgMainPic = view.find<ImageView>(R.id.imgMainPic)
}