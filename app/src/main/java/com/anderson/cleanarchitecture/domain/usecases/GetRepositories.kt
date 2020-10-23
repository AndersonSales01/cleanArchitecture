package com.anderson.cleanarchitecture.domain.usecases

import com.anderson.cleanarchitecture.data.api.Resource
import com.anderson.cleanarchitecture.domain.entities.Repository
import com.anderson.cleanarchitecture.domain.repo.RepoRepository


class GetRepositories (private var repositoryRepo: RepoRepository) {
    suspend fun execute(page: Int): Resource<ArrayList<Repository>> {
//        val result = repositoryRepo.teste(page)
//        Log.d(Constants.TAG_REPOSITORY, "GetRepositories: ${result}")
//        return result.data!!
        return repositoryRepo.getListRepositoriesWithResource(page)
    }
}