package com.accenture.cleanarchitecture.data.api.endpoints

import com.accenture.cleanarchitecture.data.entities.RepositoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryEndPoint {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun  listRepository( @Query("page") page: Int ): Response<RepositoryResponse>

}