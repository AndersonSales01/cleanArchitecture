package com.accenture.cleanarchitecture.data.mappers

import com.accenture.cleanarchitecture.data.entities.RepositoryResponse
import com.accenture.cleanarchitecture.domain.entities.Author
import com.accenture.cleanarchitecture.domain.entities.Repository


object  RepositoryMappper {

    fun toRepository(response: RepositoryResponse.RepositoryResult): Repository {
        return Repository(response.nameRepository, response.fullName, response.description,
            response.numberForks, response.numberStarts, Author(response.owner.name, response.owner.urlAvatar)
        )
    }
}