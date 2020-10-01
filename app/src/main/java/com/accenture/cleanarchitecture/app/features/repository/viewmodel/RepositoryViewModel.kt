package com.accenture.cleanarchitecture.app.features.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.accenture.cleanarchitecture.domain.usecases.GetRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryViewModel : ViewModel() {
    private var getRepositories = GetRepositories(RepoRepositoryImpl())
    private var liveDataListRepository = MutableLiveData<List<Repository>>()

    fun getRepositories() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               var list = getRepositories.execute(1)
                if (list.isNotEmpty()){
                    liveDataListRepository.postValue(list)
                }
            }
        }
    }


    fun listRepositoriesResult() : LiveData<List<Repository>> = liveDataListRepository
}