package com.accenture.cleanarchitecture.app.features

import com.accenture.cleanarchitecture.domain.usecases.GetPullRequests
import com.accenture.cleanarchitecture.domain.usecases.GetRepositories
import com.accenture.cleanarchitecture.domain.usecases.VerifyNextPageGetRepository


data class UseCases(
    val getRepositories: GetRepositories,
    val getPullRequests: GetPullRequests,
    val verifyNextPageGetRepository: VerifyNextPageGetRepository
)