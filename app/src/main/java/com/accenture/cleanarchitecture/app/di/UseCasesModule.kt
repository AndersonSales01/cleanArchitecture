package com.accenture.cleanarchitecture.app.di



import com.accenture.cleanarchitecture.domain.repo.IRepoPullRequest
import com.accenture.cleanarchitecture.domain.repo.RepoRepository
import com.accenture.cleanarchitecture.domain.usecases.GetPullRequests
import com.accenture.cleanarchitecture.domain.usecases.GetRepositories
import com.accenture.cleanarchitecture.domain.usecases.VerifyNextPageGetRepository
import dagger.Module
import dagger.Provides

@Module
 class UseCasesModule {

    @Provides
    fun provideGetRepositories(repository: RepoRepository) = GetRepositories(repository)

    @Provides
    fun provideGetPullRequests( repository: IRepoPullRequest) = GetPullRequests(repository)

    @Provides
    fun provideVerifyNextPageGetRepository() = VerifyNextPageGetRepository()
}