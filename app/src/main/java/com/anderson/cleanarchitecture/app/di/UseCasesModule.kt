package com.anderson.cleanarchitecture.app.di


import com.anderson.cleanarchitecture.domain.repo.IRepoLogin
import com.anderson.cleanarchitecture.domain.repo.IRepoPullRequest
import com.anderson.cleanarchitecture.domain.repo.RepoRepository
import com.anderson.cleanarchitecture.domain.usecases.*
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