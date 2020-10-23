package com.anderson.cleanarchitecture.data.api.endpoints

import com.anderson.cleanarchitecture.data.entities.PullRequestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestEndPoint {

    @GET("repos/{login}/{name}/pulls")
    suspend fun callPullRequest(@Path("login") loginOwner: String, @Path("name") nameRepository: String): Response<List<PullRequestResponse>>
}