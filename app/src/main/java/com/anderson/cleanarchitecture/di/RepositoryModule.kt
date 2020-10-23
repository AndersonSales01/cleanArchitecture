package com.anderson.cleanarchitecture.di

import com.anderson.cleanarchitecture.data.repository.RepoLoginImpl
import com.anderson.cleanarchitecture.data.repository.RepoPullRequestImpl
import com.anderson.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.anderson.cleanarchitecture.domain.repo.IRepoLogin
import com.anderson.cleanarchitecture.domain.repo.IRepoPullRequest
import com.anderson.cleanarchitecture.domain.repo.RepoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideGitHubRepository(repository: RepoRepositoryImpl): RepoRepository

    @Singleton
    @Binds
    abstract fun providePullRequestRepository(repository: RepoPullRequestImpl): IRepoPullRequest

    @Singleton
    @Binds
    abstract fun provideLoginRepository(repository: RepoLoginImpl): IRepoLogin
}