package com.anderson.cleanarchitecture.data.repository

import android.util.Log
import com.anderson.cleanarchitecture.constants.Constants
import com.anderson.cleanarchitecture.data.enuns.Status
import com.anderson.cleanarchitecture.data.api.Resource
import com.anderson.cleanarchitecture.data.api.endpoints.RepositoryEndPoint
import com.anderson.cleanarchitecture.data.mappers.RepositoryMappper
import com.anderson.cleanarchitecture.domain.entities.Repository
import com.anderson.cleanarchitecture.domain.repo.RepoRepository
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