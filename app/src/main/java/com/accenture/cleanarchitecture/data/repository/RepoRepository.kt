package com.accenture.cleanarchitecture.data.repository

import com.accenture.cleanarchitecture.data.api.config.RetrofitConfig
import com.accenture.cleanarchitecture.data.api.endpoints.RepositoryEndPoint
import com.accenture.cleanarchitecture.domain.entities.AuthorDTO
import com.accenture.cleanarchitecture.domain.entities.RepositoryDTO
import com.accenture.cleanarchitecture.presentation.entities.Repository

class RepoRepository {

    suspend fun getListRepositoriesRemote(page: Int) : List<RepositoryDTO>{

        val listRepositorys = ArrayList<RepositoryDTO>()

        var response = RetrofitConfig.getInstance().create(RepositoryEndPoint::class.java).listRepository(page).await()


        response.let {

            if (response.repositoryList.isNotEmpty()) {

                for (repo in response!!.repositoryList) {


                    if (repo.owner != null && repo.description != null) {

                        val repository = RepositoryDTO(repo.nameRepository, repo.fullName, repo.description,
                            repo.numberForks, repo.numberStarts, AuthorDTO(repo.owner.name, repo.owner.urlAvatar)
                        )

                        listRepositorys.add(repository)

                    }
                }

            }
        }


        return listRepositorys

    }


}