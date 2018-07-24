package com.genaku.vkclient.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.genaku.vkclient.R
import org.jetbrains.anko.find

class PhotoViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val imgPhoto = view.find<ImageView>(R.id.imgPhoto)
}