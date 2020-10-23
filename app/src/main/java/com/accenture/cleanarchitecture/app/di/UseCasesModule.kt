package com.accenture.cleanarchitecture.app.di


import com.accenture.cleanarchitecture.domain.repo.IRepoLogin
import com.accenture.cleanarchitecture.domain.repo.IRepoPullRequest
import com.accenture.cleanarchitecture.domain.repo.RepoRepository
import com.accenture.cleanarchitecture.domain.usecases.*
import com.accenture.cleanarchitecture.util.SharedPreferenceUtil
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideGetRepositories(repository: RepoRepository) = GetRepositories(repository)

    @Provides
    fun provideGetPullRequests(repository: IRepoPullRequest) = GetPullRequests(repository)

    @Provides
    fun provideRegisterLogin(repository: IRepoLogin) = RegisterLoginData(repository)

    @Provides
    fun provideVerifyNextPageGetRepository() = VerifyNextPageGetRepository()

    @Provides
    fun provideVerifyExistingLogin(repository: IRepoLogin) = VerifyIfUserExists(repository)

    @Provides
    fun provideVerifityCredentialExists(repository: IRepoLogin) = VerifyCredentialExists(repository)

}