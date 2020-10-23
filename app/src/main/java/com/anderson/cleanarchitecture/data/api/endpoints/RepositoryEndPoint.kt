package com.anderson.cleanarchitecture.data.api.endpoints

import com.anderson.cleanarchitecture.data.entities.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryEndPoint {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun  listRepository( @Query("page") page: Int ): Response<RepositoryResponse>

}