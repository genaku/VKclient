package com.genaku.vkclient.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.genaku.vkclient.GlideApp
import com.genaku.vkclient.R

class PhotosAdapter(private val photos: ArrayList<String>) : RecyclerView.Adapter<PhotoViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        GlideApp.with(mContext)
                .asDrawable()
                .fitCenter()
                .placeholder(R.color.colorPlaceholder)
                .load(photos[position])
                .into(holder.imgPhoto)
    }

}