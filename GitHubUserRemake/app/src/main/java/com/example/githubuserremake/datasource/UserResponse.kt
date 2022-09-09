package com.example.githubuserremake.datasource

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = Define.EXTRA_TABLE_NAME)
@Parcelize
data class UserResponse(
    @field:SerializedName("gists_url")
    @ColumnInfo(name = "gists_url")
    var gistsUrl: String? = null,

    @field:SerializedName("repos_url")
    @ColumnInfo(name = "repos_url")
    var reposUrl: String? = null,

    @field:SerializedName("following_url")
    @ColumnInfo(name = "following_url")
    var followingUrl: String? = null,

    @field:SerializedName("starred_url")
    @ColumnInfo(name = "starred_url")
    var starredUrl: String? = null,

    @field:SerializedName("login")
    @ColumnInfo(name = "login")
    var login: String? = null,

    @field:SerializedName("followers_url")
    @ColumnInfo(name = "followers_url")
    var followersUrl: String? = null,

    @field:SerializedName("type")
    @ColumnInfo(name = "type")
    var type: String? = null,

    @field:SerializedName("url")
    @ColumnInfo(name = "url")
    var url: String? = null,

    @field:SerializedName("subscriptions_url")
    @ColumnInfo(name = "subscriptions_url")
    var subscriptionsUrl: String? = null,

    @field:SerializedName("received_events_url")
    @ColumnInfo(name = "received_events_url")
    var receivedEventsUrl: String? = null,

    @field:SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,

    @field:SerializedName("events_url")
    @ColumnInfo(name = "events_url")
    var eventsUrl: String? = null,

    @field:SerializedName("html_url")
    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = null,

    @field:SerializedName("site_admin")
    @ColumnInfo(name = "site_admin")
    var siteAdmin: Boolean? = null,

    @field:SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,

    @field:SerializedName("gravatar_id")
    @ColumnInfo(name = "gravatar_id")
    var gravatarId: String? = null,

    @field:SerializedName("node_id")
    @ColumnInfo(name = "node_id")
    var nodeId: String? = null,

    @field:SerializedName("organizations_url")
    @ColumnInfo(name = "organizations_url")
    var organizationsUrl: String? = null
) : Parcelable
