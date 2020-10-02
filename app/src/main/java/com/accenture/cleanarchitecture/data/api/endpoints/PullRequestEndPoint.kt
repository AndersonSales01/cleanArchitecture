package com.accenture.cleanarchitecture.data.api.endpoints

import com.accenture.cleanarchitecture.data.entities.PullRequestResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestEndPoint {

    @GET("repos/{login}/{name}/pulls")
     fun callPullRequest(@Path("login") loginOwner: String, @Path("name") nameRepository: String): Deferred<List<PullRequestResponse>>
}