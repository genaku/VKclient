package com.genaku.vkclient.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.genaku.vkclient.GlideApp
import com.genaku.vkclient.R
import com.genaku.vkclient.adapters.PhotosAdapter
import com.genaku.vkclient.navigation.screens.PostScreen
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.lay_author.*
import kotlinx.android.synthetic.main.lay_likes.*
import kotlinx.android.synthetic.main.activity_lay_reposted.*
import org.jetbrains.anko.find

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class PostActivity : NavigationalActivity(
        isChildActivity = true
) {

    private val mUiPostData by lazy {
        val screen = mNavigator.getScreen<PostScreen>(this)
        screen.uiPostData
    }

    private val mIsRepost by lazy {
        mUiPostData.copyHistory.size > 0
    }

    override val layoutResId: Int
        get() = if (mIsRepost) R.layout.activity_repost else R.layout.activity_post

    override fun setupView() {
        val glideRequest = GlideApp.with(this).asDrawable().fitCenter()
        mUiPostData.apply {
            tvAuthor.text = authorName
            tvText.text = text
            tvLikes.text = likesCount
            tvReposts.text = repostsCount
            tvDate.text = date
            glideRequest
                    .load(authorAvatarUrl)
                    .into(imgAuthor)
            val rvPictures = find<RecyclerView>(R.id.rvPictures)
            rvPictures.layoutManager = LinearLayoutManager(this@PostActivity, LinearLayoutManager.HORIZONTAL, false)
            if (mIsRepost) {
                val repost = copyHistory[0]
                tvRepostedAuthor.text = repost.authorName
                tvRepostedDate.text = repost.date
                tvRepostedText.text = repost.text
                glideRequest
                        .load(repost.authorAvatarUrl)
                        .into(imgRepostedAuthor)
                rvPictures.adapter = PhotosAdapter(repost.pictures)
            } else {
                rvPictures.adapter = PhotosAdapter(pictures)
            }
        }
    }

    override fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        mNavigator.back()
        return true
    }

}