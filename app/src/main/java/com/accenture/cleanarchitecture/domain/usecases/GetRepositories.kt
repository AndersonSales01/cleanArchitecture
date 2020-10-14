package com.accenture.cleanarchitecture.domain.usecases

import com.accenture.cleanarchitecture.data.api.Resource
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.accenture.cleanarchitecture.domain.repo.RepoRepository
import javax.inject.Inject


class GetRepositories (private var repositoryRepo: RepoRepository) {
    suspend fun execute(page: Int): Resource<ArrayList<Repository>> {
//        val result = repositoryRepo.teste(page)
//        Log.d(Constants.TAG_REPOSITORY, "GetRepositories: ${result}")
//        return result.data!!
        return repositoryRepo.getListRepositoriesWithResource(page)
    }
}