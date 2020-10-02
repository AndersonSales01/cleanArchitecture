package com.accenture.cleanarchitecture.app.features.repository.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.cleanarchitecture.R
import com.accenture.cleanarchitecture.app.common.BaseActivity
import com.accenture.cleanarchitecture.app.features.pullrequest.ui.view.PullRequestActivity
import com.accenture.cleanarchitecture.app.features.repository.ui.Router
import com.accenture.cleanarchitecture.app.features.repository.ui.adapter.RepositoryAdapter
import com.accenture.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.domain.entities.Repository
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : BaseActivity(), Router {

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var manager: LinearLayoutManager
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        initialize()

    }

    override fun initialize() {
        initViews()

        repositoryViewModel = ViewModelProviders.of(this)
            .get(RepositoryViewModel::class.java)

        repositoryViewModel.getRepositories()

        observables()

        scrollInfinite()
    }

    override fun initViews() {
        repositoryAdapter = RepositoryAdapter(this,this)

        manager = LinearLayoutManager(this)

        list_repositories.layoutManager = manager
        list_repositories.adapter = repositoryAdapter
    }

    override fun observables() {
        repositoryViewModel.listRepositoriesResult().observe(this, Observer { listRepertories ->
            repositoryAdapter.loadRepository(listRepertories)
        })
    }

    override fun toGoPullRequestScreen(repository: Repository) {
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra(Constants.NAME_OWNER, repository.author.name)
        intent.putExtra(Constants.NAME_REPOSITORY, repository.name)
        startActivity(intent)
    }

    fun scrollInfinite() {

        list_repositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                repositoryViewModel.getMoreItems(manager.childCount, manager.findFirstVisibleItemPosition(), manager.itemCount)

            }

        })
    }


}
