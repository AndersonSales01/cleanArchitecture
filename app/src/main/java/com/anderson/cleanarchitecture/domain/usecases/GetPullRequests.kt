package com.anderson.cleanarchitecture.domain.usecases

import com.anderson.cleanarchitecture.domain.entities.PullRequest
import com.anderson.cleanarchitecture.domain.repo.IRepoPullRequest

class GetPullRequests(private var pullRequestIRepo: IRepoPullRequest) {
    suspend fun execute(nameOwner: String, nameRepository: String): List<PullRequest> {
        return pullRequestIRepo.loadPullRequest(nameOwner, nameRepository)
    }
}