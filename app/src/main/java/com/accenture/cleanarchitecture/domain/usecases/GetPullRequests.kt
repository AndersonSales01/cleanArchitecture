package com.accenture.cleanarchitecture.domain.usecases

import com.accenture.cleanarchitecture.domain.entities.PullRequest
import com.accenture.cleanarchitecture.domain.repo.IRepoPullRequest

class GetPullRequests(var pullRequestIRepo: IRepoPullRequest) {
    suspend fun execute(nameOwner: String, nameRepository: String): List<PullRequest> {
        return pullRequestIRepo.loadPullRequest(nameOwner, nameRepository)
    }
}