package com.accenture.cleanarchitecture.data.repository

import android.util.Log
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.data.api.config.RetrofitConfig
import com.accenture.cleanarchitecture.data.api.endpoints.RepositoryEndPoint
import com.accenture.cleanarchitecture.data.mappers.RepositoryMappper
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.accenture.cleanarchitecture.domain.repo.RepoRepository

class RepoRepositoryImpl : RepoRepository {

    override suspend fun getListRepositoriesRemote(page: Int): List<Repository> {

        val listRepositorys = ArrayList<Repository>()

        Log.d(Constants.TAG_REPOSITORY, "Page: $page")

        var response =
            RetrofitConfig.getInstance().create(RepositoryEndPoint::class.java).listRepository(page)
                .await()

        Log.d(Constants.TAG_REPOSITORY, "Response: ${response}")

        response.let {

            if (response.repositoryList.isNotEmpty()) {

                for (repo in response!!.repositoryList) {

                    if (repo.owner != null && repo.description != null) {

                        val repository = RepositoryMappper.toRepository(repo)

                        listRepositorys.add(repository)

                    }
                }
            }
        }

        return listRepositorys
    }
}