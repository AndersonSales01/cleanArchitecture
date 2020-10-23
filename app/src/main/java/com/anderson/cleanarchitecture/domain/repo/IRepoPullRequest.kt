package com.anderson.cleanarchitecture.domain.repo

import com.anderson.cleanarchitecture.domain.entities.PullRequest

interface IRepoPullRequest {
    suspend fun loadPullRequest(nameOwner: String, nameRepository: String): List<PullRequest>
}