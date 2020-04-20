package com.accenture.cleanarchitecture.domain.usecases

import com.accenture.cleanarchitecture.data.repository.RepoRepository
import com.accenture.cleanarchitecture.presentation.entities.Author
import com.accenture.cleanarchitecture.presentation.entities.Repository

class GetRepositories {
    private var repoRepositories: RepoRepository = RepoRepository()
    suspend fun execute(page: Int): List<Repository> {
        var listRepositorys = ArrayList<Repository>()
        repoRepositories.getListRepositoriesRemote(page).map { repositoryDto ->

            listRepositorys.add(
                Repository(
                    repositoryDto.name,
                    repositoryDto.fullName,
                    repositoryDto.description,
                    repositoryDto.numberForks,
                    repositoryDto.numberStarts,
                    Author(repositoryDto.author.name, repositoryDto.author.urlAvatar)
                )
            )
        }

        return listRepositorys
    }


}