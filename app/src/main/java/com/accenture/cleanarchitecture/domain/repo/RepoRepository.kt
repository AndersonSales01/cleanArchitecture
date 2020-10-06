package com.accenture.cleanarchitecture.domain.repo

import com.accenture.cleanarchitecture.data.api.Resource
import com.accenture.cleanarchitecture.domain.entities.Repository

interface RepoRepository {
    suspend fun getListRepositoriesRemote(page: Int) : List<Repository>
    suspend fun getListRepositoriesWithResource(page: Int): Resource<ArrayList<Repository>>
}