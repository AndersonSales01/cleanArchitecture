package com.accenture.cleanarchitecture.di

import com.accenture.cleanarchitecture.data.repository.RepoLoginImpl
import com.accenture.cleanarchitecture.data.repository.RepoPullRequestImpl
import com.accenture.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.accenture.cleanarchitecture.domain.repo.IRepoLogin
import com.accenture.cleanarchitecture.domain.repo.IRepoPullRequest
import com.accenture.cleanarchitecture.domain.repo.RepoRepository
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