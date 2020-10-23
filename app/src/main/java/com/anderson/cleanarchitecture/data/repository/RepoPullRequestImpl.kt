package com.anderson.cleanarchitecture.data.repository

import android.util.Log
import com.anderson.cleanarchitecture.constants.Constants
import com.anderson.cleanarchitecture.data.api.endpoints.PullRequestEndPoint
import com.anderson.cleanarchitecture.data.mappers.PullRequestMapper
import com.anderson.cleanarchitecture.domain.entities.PullRequest
import com.anderson.cleanarchitecture.domain.repo.IRepoPullRequest
import java.util.ArrayList
import javax.inject.Inject

class RepoPullRequestImpl @Inject constructor( private var endPoint: PullRequestEndPoint) : IRepoPullRequest {

    override suspend fun loadPullRequest(
        nameOwner: String,
        nameRepository: String
    ): List<PullRequest> {

        val listPullRequest = ArrayList<PullRequest>()

        val response = endPoint.callPullRequest(nameOwner, nameRepository)
        try {

            if (response.isSuccessful) {

                response.let {

                    if (response.body()!!.isNotEmpty()) {

                        for (pullRequest in response.body()!!) {

                            val pullRequest = PullRequestMapper.toPullRequestObject(pullRequest)

                            listPullRequest.add(pullRequest)
                        }
                    }
                }
            }

        } catch (e: Exception) {
            Log.d(
                Constants.TAG_PULLREQUEST,
                "Message: ${response.message()} Status Code: ${response.code()}"
            )
        }

        return listPullRequest
    }

}