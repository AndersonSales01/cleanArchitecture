package com.anderson.cleanarchitecture.data.entities

import com.google.gson.annotations.SerializedName

class PullRequestResponse(
    val title: String,
    val body: String,
    @SerializedName("created_at")
    val dataCreatePullRequest: String,
    @SerializedName("html_url")
    val urlPullRequest: String,
    @SerializedName("user")
    val user: UserResult
) {

    class UserResult(
        @SerializedName("login")
        val name: String,
        @SerializedName("avatar_url")
        val avatarURL: String
    )
}