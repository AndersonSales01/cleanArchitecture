package com.accenture.cleanarchitecture.data.repository

import android.util.Log
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.data.api.config.RetrofitConfig
import com.accenture.cleanarchitecture.data.api.endpoints.PullRequestEndPoint
import com.accenture.cleanarchitecture.data.mappers.PullRequestMapper
import com.accenture.cleanarchitecture.domain.entities.PullRequest
import com.accenture.cleanarchitecture.domain.repo.IRepoPullRequest
import java.util.ArrayList

class RepoPullRequestImpl : IRepoPullRequest {

    override suspend fun loadPullRequest(
        nameOwner: String,
        nameRepository: String
    ): List<PullRequest> {

        val listPullRequest = ArrayList<PullRequest>()

        val response = RetrofitConfig.getInstance().create(PullRequestEndPoint::class.java)
            .callPullRequest(nameOwner, nameRepository)
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