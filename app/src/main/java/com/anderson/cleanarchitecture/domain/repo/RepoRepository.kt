package com.anderson.cleanarchitecture.domain.repo

import com.anderson.cleanarchitecture.data.api.Resource
import com.anderson.cleanarchitecture.domain.entities.Repository

interface RepoRepository {
    suspend fun getListRepositoriesRemote(page: Int) : List<Repository>
    suspend fun getListRepositoriesWithResource(page: Int): Resource<ArrayList<Repository>>
}