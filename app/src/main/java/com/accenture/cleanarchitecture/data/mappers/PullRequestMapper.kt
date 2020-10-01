package com.accenture.cleanarchitecture.data.mappers

import com.accenture.cleanarchitecture.data.entities.PullRequestResponse
import com.accenture.cleanarchitecture.domain.entities.PullRequest
import com.accenture.cleanarchitecture.domain.entities.User

object PullRequestMapper {
    fun toPullRequestObject(response: PullRequestResponse): PullRequest {
        return PullRequest(
            response.title, response.body, response.dataCreatePullRequest,
            response.urlPullRequest, User(response.user.name, response.user.avatarURL)
        )
    }
}