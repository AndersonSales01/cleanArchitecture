package com.anderson.cleanarchitecture.app.features

import com.anderson.cleanarchitecture.domain.usecases.GetPullRequests
import com.anderson.cleanarchitecture.domain.usecases.GetRepositories
import com.anderson.cleanarchitecture.domain.usecases.VerifyNextPageGetRepository


data class UseCases(
    val getRepositories: GetRepositories,
    val getPullRequests: GetPullRequests,
    val verifyNextPageGetRepository: VerifyNextPageGetRepository
)