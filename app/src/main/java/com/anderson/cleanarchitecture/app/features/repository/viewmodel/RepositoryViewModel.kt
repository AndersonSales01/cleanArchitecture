package com.anderson.cleanarchitecture.app.features.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.cleanarchitecture.data.enuns.Status
import com.anderson.cleanarchitecture.domain.entities.Repository
import com.anderson.cleanarchitecture.domain.usecases.GetRepositories
import com.anderson.cleanarchitecture.domain.usecases.VerifyNextPageGetRepository
import com.anderson.cleanarchitecture.util.SharedPreferenceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(var sharedPref: SharedPreferenceUtil) : ViewModel() {
    //private var getRepositories = GetRepositories(RepoRepositoryImpl(context))
    //private var verifyNextPage = VerifyNextPageGetRepository()
    @Inject
    lateinit var getRepositories: GetRepositories
    @Inject
    lateinit var verifyNextPage: VerifyNextPageGetRepository

    private var liveDataListRepository = MutableLiveData<List<Repository>>()
    private var _MessageLiveData = MutableLiveData<String>()

    private var page = 1

    fun getRepositories() {

//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//               var list = getRepositories.execute(page)
//                if (list.isNotEmpty()){
//                    liveDataListRepository.postValue(list)
//                }
//                page++
//            }
//        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var result = getRepositories.execute(page)
                if (result.status == Status.SUCCESS){
                    if(result?.data!!.isNotEmpty()){
                        liveDataListRepository.postValue(result.data!!)
                        page++
                    }
                }else {
                    _MessageLiveData.postValue(result.message)
                }

            }
        }
    }

    fun getMoreItems(currentItems: Int, scrollOutItems: Int, totalItems: Int) {
        if (verifyNextPage.execute(currentItems,scrollOutItems,totalItems)) {
            getRepositories()
        }
    }

    fun userLogout(){
        sharedPref.saveStatusLogged(false)
    }

    fun listRepositoriesResult() : LiveData<List<Repository>> = liveDataListRepository
    fun showToastMessage() : LiveData<String> = _MessageLiveData
}