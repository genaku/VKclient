package com.genaku.remote

import android.webkit.CookieManager
import com.genaku.domain.model.LoginData
import com.genaku.domain.model.NewsFeedData
import com.genaku.domain.model.PostData
import com.genaku.remote.jsonmodel.Post
import com.genaku.remote.jsonmodel.toModel
import com.genaku.repository.interfaces.IRemoteRepository
import org.mym.plog.PLog

/**
 * Created by Gena Kuchergin on 20.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class RemoteRepository : IRemoteRepository {

    private val mApi = Api()
    private val mAuthors = Authors()

    override fun setLoginData(data: LoginData) {
        mApi.setLoginData(data)
    }

    override fun logout() {
        mApi.setLoginData(LoginData("", -1))
        val cookieManager = CookieManager.getInstance()
        @Suppress("DEPRECATION")
        cookieManager.removeAllCookie()
    }

    @Throws(RemoteRepoException::class)
    override fun getNews(startFrom: String, count: Int): NewsFeedData {
        val response = mApi.getNews(startFrom, count)
        mAuthors.addGroups(response.groups)
        mAuthors.addProfiles(response.profiles)
        val result = NewsFeedData(
                prevKey = startFrom,
                nextKey = response.next_from ?: "",
                items = preparePosts(response.items)
        )
        return result
    }

    private fun preparePosts(posts: ArrayList<Post>?): ArrayList<PostData> {
        posts ?: return ArrayList()
        val result = ArrayList<PostData>()
        for (item in posts) {
            result.add(item.toModel { sourceId -> mAuthors.getAuthor(sourceId) })
        }
        return result
    }

}