package com.genaku.remote

import com.genaku.domain.model.Author
import com.genaku.remote.jsonmodel.Profile
import com.genaku.remote.jsonmodel.VkGroup
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Gena Kuchergin on 22.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class Authors {

    private val mProfiles: ConcurrentHashMap<Long, Profile> = ConcurrentHashMap()
    private val mGroups: ConcurrentHashMap<Long, VkGroup> = ConcurrentHashMap()

    fun addProfiles(profiles: ArrayList<Profile>?) {
        profiles?.forEach {
            mProfiles[it.id] = it
        }
    }

    fun addGroups(groups: ArrayList<VkGroup>?) {
        groups?.forEach {
            mGroups[it.id] = it
        }
    }

    fun getAuthor(sourceId: Long): Author {
        if (sourceId > 0) {
            val profile = mProfiles[sourceId] ?: return Author("", "")
            return with(profile) { Author(name = "$firstName $lastName", avatarUrl = avatarUrl) }
        } else {
            val group = mGroups[-sourceId] ?: return Author("", "")
            return with(group) { Author(name = name, avatarUrl = avatarUrl) }
        }
    }

}