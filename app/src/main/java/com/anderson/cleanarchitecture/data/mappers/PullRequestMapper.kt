package com.anderson.cleanarchitecture.data.mappers

import com.anderson.cleanarchitecture.data.entities.PullRequestResponse
import com.anderson.cleanarchitecture.domain.entities.PullRequest
import com.anderson.cleanarchitecture.domain.entities.User

object PullRequestMapper {
    fun toPullRequestObject(response: PullRequestResponse): PullRequest {
        return PullRequest(
            response.title, response.body, response.dataCreatePullRequest,
            response.urlPullRequest, User(response.user.name, response.user.avatarURL)
        )
    }
}