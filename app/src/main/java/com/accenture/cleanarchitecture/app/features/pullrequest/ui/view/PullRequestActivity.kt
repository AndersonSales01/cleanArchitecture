package com.accenture.cleanarchitecture.app.features.pullrequest.ui.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.accenture.cleanarchitecture.R
import com.accenture.cleanarchitecture.app.common.BaseActivity
import com.accenture.cleanarchitecture.app.features.pullrequest.ui.adapter.PullRequestAdapter
import com.accenture.cleanarchitecture.app.features.pullrequest.viewmodel.PullRequestViewModel
import com.accenture.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import com.accenture.cleanarchitecture.constants.Constants
import kotlinx.android.synthetic.main.activity_pull_request.*
import javax.inject.Inject

class PullRequestActivity :  BaseActivity() {

   // private lateinit var pullRequestViewModel: PullRequestViewModel
    private lateinit var pullRequestAdapter: PullRequestAdapter
    private  lateinit var nameRepository: String
    private  lateinit var nameOwner: String

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val pullRequestViewModel: PullRequestViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[PullRequestViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        initialize()
    }

    override fun initialize(){

//        pullRequestViewModel = ViewModelProviders.of(this)
//            .get(PullRequestViewModel::class.java)

        subComponent.inject(this)

        initViews()

        observables()

        pullRequestViewModel.callRequestPullResquest(nameOwner, nameRepository)

    }

    override fun initViews() {

        pullRequestAdapter = PullRequestAdapter(this)

        recyclerView_pull_request.layoutManager = LinearLayoutManager(this)
        recyclerView_pull_request.adapter = pullRequestAdapter

        val intent = intent
        nameRepository = intent.getStringExtra(Constants.NAME_REPOSITORY)
        nameOwner = intent.getStringExtra(Constants.NAME_OWNER)

        val actionbar = supportActionBar

        if (nameRepository != "") {

            if (actionbar != null) {
                actionbar.title = nameRepository
            }
        }
    }

    override fun observables() {

        pullRequestViewModel.getListPullRequests().observe(this, Observer { listPullRquests ->
            pullRequestAdapter.loadPullRequest(listPullRquests!!)

        })

        pullRequestViewModel.showProgress().observe(this, Observer { isShow ->
            if (isShow!!) {
                progress_pull_request.visibility = View.GONE

            }
        })
    }
}