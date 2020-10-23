package com.anderson.cleanarchitecture.data.entities

import com.google.gson.annotations.SerializedName

class RepositoryResponse(
    @SerializedName("items")
    val repositoryList: List<RepositoryResult>
) {
    data class RepositoryResult(
        @SerializedName("name")
        val nameRepository: String = "",
        @SerializedName("full_name")
        val fullName: String = "",
        val description: String = "",
        @SerializedName("forks")
        val numberForks: Int = 0,
        @SerializedName("watchers")
        val numberStarts: Int = 0,
        @SerializedName("owner")
        val owner: OwnerResult
    )

    data class OwnerResult(
        @SerializedName("login")
        val name: String = "",
        @SerializedName("avatar_url")
        val urlAvatar: String = ""
    )

}
