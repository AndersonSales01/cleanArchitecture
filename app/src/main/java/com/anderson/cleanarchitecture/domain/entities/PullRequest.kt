package com.anderson.cleanarchitecture.domain.entities

class PullRequest(
    val title: String,
    val body: String,
    val dataCreatePullRequest: String,
    val urlPullRequest: String,
    val user: User
) {
}