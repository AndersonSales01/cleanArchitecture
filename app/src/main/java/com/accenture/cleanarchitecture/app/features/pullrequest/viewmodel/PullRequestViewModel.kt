package com.accenture.cleanarchitecture.app.features.pullrequest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.cleanarchitecture.data.repository.RepoPullRequestImpl
import com.accenture.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.accenture.cleanarchitecture.domain.entities.PullRequest
import com.accenture.cleanarchitecture.domain.usecases.GetPullRequests
import com.accenture.cleanarchitecture.domain.usecases.GetRepositories
import com.github.kittinunf.result.coroutines.SuspendableResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PullRequestViewModel :  ViewModel() {

    private var listPullRequest = mutableListOf<PullRequest>()

    private var liveDataListPullRequestRepository = MutableLiveData<List<PullRequest>>()
    private var showProgress = MutableLiveData<Boolean>()

    private var result  = MutableLiveData<Int>()

    private var getPullRequests = GetPullRequests(RepoPullRequestImpl())


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