package com.anderson.cleanarchitecture.data.mappers

import com.anderson.cleanarchitecture.data.entities.RepositoryResponse
import com.anderson.cleanarchitecture.domain.entities.Author
import com.anderson.cleanarchitecture.domain.entities.Repository


object  RepositoryMappper {

    fun toRepository(response: RepositoryResponse.RepositoryResult): Repository {
        return Repository(response.nameRepository, response.fullName, response.description,
            response.numberForks, response.numberStarts, Author(response.owner.name, response.owner.urlAvatar)
        )
    }
}