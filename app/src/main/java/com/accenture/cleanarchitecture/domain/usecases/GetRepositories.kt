package com.accenture.cleanarchitecture.domain.usecases

import com.accenture.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.accenture.cleanarchitecture.domain.repo.RepoRepository


class GetRepositories(var repositoryRepo: RepoRepository) {
    suspend fun execute(page: Int): List<Repository> {
        return repositoryRepo.getListRepositoriesRemote(page)
    }
}