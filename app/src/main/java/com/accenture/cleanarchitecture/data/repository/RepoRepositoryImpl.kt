package com.accenture.cleanarchitecture.data.repository

import android.content.Context
import android.util.Log
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.data.enuns.Status
import com.accenture.cleanarchitecture.data.api.Resource
import com.accenture.cleanarchitecture.data.api.config.RetrofitConfig
import com.accenture.cleanarchitecture.data.api.endpoints.RepositoryEndPoint
import com.accenture.cleanarchitecture.data.mappers.RepositoryMappper
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.accenture.cleanarchitecture.domain.repo.RepoRepository
import java.lang.Exception
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(private var endPoint: RepositoryEndPoint) : RepoRepository {

    override suspend fun getListRepositoriesRemote(page: Int): List<Repository> {

        val listRepositorys = ArrayList<Repository>()

        Log.d(Constants.TAG_REPOSITORY, "Page: $page")

        var response = endPoint.listRepository(page)

        Log.d(Constants.TAG_REPOSITORY, "Response: ${response}")
        try {
            if (response.isSuccessful) {

                response.let {

                    if (response?.body()!!.repositoryList.isNotEmpty()) {

                        for (repo in response?.body()!!.repositoryList) {

                            if (repo.owner != null && repo.description != null) {

                                val repository = RepositoryMappper.toRepository(repo)

                                listRepositorys.add(repository)

                            }
                        }
                    }
                }
            }

        } catch (e: Exception) {
            Log.d(
                Constants.TAG_REPOSITORY,
                "Message: ${response.message()} Status Code: ${response.code()}"
            )
        }

        return listRepositorys
    }

    override suspend fun getListRepositoriesWithResource(page: Int): Resource<ArrayList<Repository>> {

        var listRepositorys = ArrayList<Repository>()

        lateinit var result: Resource<ArrayList<Repository>>

        var data = endPoint.listRepository(page)

        try {
            for (repo in data?.body()!!.repositoryList) {

                if (repo.owner != null && repo.description != null) {

                    val repository = RepositoryMappper.toRepository(repo)

                    listRepositorys.add(repository)

                }
            }

            result = Resource.result(status = Status.SUCCESS,data = listRepositorys, statusCode = data.code(), message = "")

            Log.d(Constants.TAG_REPOSITORY, "Sucess: ${result}")
        } catch (e: Exception) {
            result = Resource.result(status = Status.ERROR,data = ArrayList(), statusCode = data.code(), message = "${data.message()}")
            Log.d(Constants.TAG_REPOSITORY, "Error: ${result}")
        }

        return result
    }
}