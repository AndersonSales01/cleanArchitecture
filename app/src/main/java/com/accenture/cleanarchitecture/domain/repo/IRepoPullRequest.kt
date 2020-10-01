package com.accenture.cleanarchitecture.domain.repo

import com.accenture.cleanarchitecture.domain.entities.PullRequest

interface IRepoPullRequest {
    suspend fun loadPullRequest(nameOwner: String, nameRepository: String): List<PullRequest>
}