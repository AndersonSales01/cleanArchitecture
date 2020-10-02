package com.accenture.cleanarchitecture.app.features.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.accenture.cleanarchitecture.domain.usecases.GetRepositories
import com.accenture.cleanarchitecture.domain.usecases.VerifyNextPageGetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryViewModel : ViewModel() {
    private var getRepositories = GetRepositories(RepoRepositoryImpl())
    private var verifyNextPage = VerifyNextPageGetRepository()
    private var liveDataListRepository = MutableLiveData<List<Repository>>()
    private var page = 1

    fun getRepositories() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               var list = getRepositories.execute(page)
                if (list.isNotEmpty()){
                    liveDataListRepository.postValue(list)
                }
                page++
            }
        }
    }

    fun getMoreItems(currentItems: Int, scrollOutItems: Int, totalItems: Int) {
        if (verifyNextPage.execute(currentItems,scrollOutItems,totalItems)) {
            getRepositories()
        }
    }

    fun listRepositoriesResult() : LiveData<List<Repository>> = liveDataListRepository
}