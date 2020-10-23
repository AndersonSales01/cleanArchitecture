package com.anderson.cleanarchitecture.app.features.pullrequest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.cleanarchitecture.domain.entities.PullRequest
import com.anderson.cleanarchitecture.domain.usecases.GetPullRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PullRequestViewModel @Inject constructor()  :  ViewModel() {

    private var listPullRequest = mutableListOf<PullRequest>()

    private var liveDataListPullRequestRepository = MutableLiveData<List<PullRequest>>()
    private var showProgress = MutableLiveData<Boolean>()

    private var result  = MutableLiveData<Int>()

   // private var getPullRequests = GetPullRequests(RepoPullRequestImpl())

    @Inject
    lateinit var getPullRequests: GetPullRequests


    fun callRequestPullResquest(nameOwner: String, nameRepository: String) {

        if (nameOwner != null && nameOwner != "" && nameRepository != null && nameRepository != "") {

            requestPullRequest(nameOwner, nameRepository)

        }

    }


    private fun requestPullRequest(nameOwner: String, nameRepository: String) {

        viewModelScope.launch {

            withContext(Dispatchers.IO) {

                showProgress.postValue(true)

                var listPullRequests = getPullRequests.execute(nameOwner,nameRepository)
                if (listPullRequests.isNotEmpty()){
                    liveDataListPullRequestRepository.postValue(listPullRequests)
                    showProgress.postValue(false)
                }


//
//
//
//                SuspendableResult.of<List<PullRequest>, Exception> {
//
//                    repository.loadPullRequest(nameOwner, nameRepository)
//
//                }.fold(
//                    success = { list ->
//
//                        liveDataListPullRequestRepository.postValue(list)
//                        showProgress.postValue(false)
//
//                    },
//                    failure = { error ->
//
//                        Log.d("Error Request", error.toString())
//                        showProgress.postValue(false)
//
//                    }
//                )
            }
        }
    }


    fun getListPullRequests(): LiveData<List<PullRequest>> = liveDataListPullRequestRepository
    fun showProgress(): LiveData<Boolean> = showProgress

    fun Soma(valor1: Int, valor2: Int) {

        result.postValue(valor1 + valor2)
    }
    fun diminuicao(valor1: Int, valor2: Int) {

        result.postValue( valor1 - valor2)
    }

    fun getResultCalculate(): LiveData<Int> = result

}