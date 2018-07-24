package com.genaku.vkclient.paging.delegates.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.genaku.vkclient.R
import org.jetbrains.anko.find

class RepostViewHolder(view: View): PostViewHolder(view) {
    val tvRespostedAuthor = view.find<TextView>(R.id.tvRepostedAuthor)
    val tvRepostedDate = view.find<TextView>(R.id.tvRepostedDate)
    val tvRepostedText = view.find<TextView>(R.id.tvRepostedText)
    val imgRepostedAuthor = view.find<ImageView>(R.id.imgRepostedAuthor)
}